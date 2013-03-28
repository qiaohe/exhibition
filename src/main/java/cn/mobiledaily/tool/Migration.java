package cn.mobiledaily.tool;

import cn.mobiledaily.domain.*;
import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.CheckInEntry;
import cn.mobiledaily.domain.mobile.Location;
import cn.mobiledaily.domain.mobile.pushnotification.MobilePlatform;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.service.UserService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Migration {
    public static void main(String[] args) {
        Migration migration = new Migration();
        try {
            migration.start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            migration.stop();
        }
    }

    private Connection conn;
    private Statement st;
    private AbstractApplicationContext context;
    private UserService userService;
    private MongoOperations mongoOperations;
    private ExhibitionService exhibitionService;
    private Map<String, User> userMap_o_id_2_n_obj = new HashMap<>();
    private Map<String, Exhibition> exhibitionMap_o_id_2_n_obj = new HashMap<>();

    public Migration() {
        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/exhibitionDB?user=root&useUnicode=true&characterEncoding=UTF-8");
            conn = DriverManager.getConnection("jdbc:mysql://180.168.35.37/exhibitionDB?user=root&password=tmsql2012#&useUnicode=true&characterEncoding=UTF-8");
            st = conn.createStatement();
            context = new ClassPathXmlApplicationContext("/applicationContext-migration.xml");
            userService = context.getBean(UserService.class);
            mongoOperations = context.getBean(MongoOperations.class);
            exhibitionService = context.getBean(ExhibitionService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        doUser();
        doExhibition();
        doEventSchedule();
        doExhibitor();
        doSpeaker();
        doSponsor();
        doPushMessage();
        doAttendee();
    }

    public void stop() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        context.close();
    }

    private void dropCollection(String collectionName) {
        mongoOperations.dropCollection(collectionName);
    }

    private void dropCollectionsWithSuffix(String suffix) {
        Set<String> collectionNames = new HashSet<>();
        for (String name : mongoOperations.getCollectionNames()) {
            if (name.endsWith(suffix)) {
                collectionNames.add(name);
            }
        }
        for (String collectionName : collectionNames) {
            mongoOperations.dropCollection(collectionName);
        }
    }

    private void doUser() throws Exception {
        dropCollection("user");
        ResultSet rs = st.executeQuery("SELECT * FROM user");
        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAuthority(rs.getString("authority"));

            userService.register(user.getUsername(), user.getPassword(), user.getEmail());
            userService.changeAuthority(user.getUsername(), user.getAuthority());
            userMap_o_id_2_n_obj.put(rs.getString("id"), userService.findByUsername(user.getUsername()));
        }
        rs.close();
    }

    private void doExhibition() throws Exception {
        dropCollection("exhibition");
        ResultSet rs = st.executeQuery("SELECT * FROM exhibition");
        while (rs.next()) {
            Exhibition exhibition = new Exhibition();
            exhibition.setCreatedBy(userMap_o_id_2_n_obj.get(rs.getString("created_by")));
            exhibition.setCreatedAt(rs.getTimestamp("created_at"));
            exhibition.setUpdatedBy(userMap_o_id_2_n_obj.get(rs.getString("updated_by")));
            exhibition.setUpdatedAt(rs.getTimestamp("updated_at"));

            exhibition.setCode(rs.getString("code"));
            exhibition.setName(rs.getString("name"));
            exhibition.setDescription(rs.getString("description"));
            exhibition.setAddress(rs.getString("address"));
            exhibition.setStartDate(rs.getTimestamp("start_date"));
            exhibition.setEndDate(rs.getTime("end_date"));
            exhibition.setOrganizer(rs.getString("organizer"));

            exhibitionService.save(exhibition);
            exhibitionMap_o_id_2_n_obj.put(rs.getString("id"), exhibition);
        }
        rs.close();
    }

    private void doEventSchedule() throws Exception {
        dropCollectionsWithSuffix(new EventSchedule().getCollectionSuffix());
        ResultSet rs = st.executeQuery("SELECT * FROM event_schedule");
        while (rs.next()) {
            EventSchedule eventSchedule = new EventSchedule();
            eventSchedule.setCreatedBy(userMap_o_id_2_n_obj.get(rs.getString("created_by")));
            eventSchedule.setCreatedAt(rs.getTimestamp("created_at"));
            eventSchedule.setUpdatedBy(userMap_o_id_2_n_obj.get(rs.getString("updated_by")));
            eventSchedule.setUpdatedAt(rs.getTimestamp("updated_at"));
            eventSchedule.setExhibition(exhibitionMap_o_id_2_n_obj.get(rs.getString("exhibition")));

            eventSchedule.setName(rs.getString("name"));
            eventSchedule.setDescription(rs.getString("description"));
            eventSchedule.setDateFrom(rs.getTimestamp("date_from"));
            eventSchedule.setDateTo(rs.getTimestamp("date_to"));
            eventSchedule.setPlace(rs.getString("place"));

            exhibitionService.save(eventSchedule);
        }
        rs.close();
    }

    private void doExhibitor() throws Exception {
        dropCollectionsWithSuffix(new Exhibitor().getCollectionSuffix());
        ResultSet rs = st.executeQuery("SELECT * FROM exhibitor");
        while (rs.next()) {
            Exhibitor exhibitor = new Exhibitor();
            exhibitor.setCreatedBy(userMap_o_id_2_n_obj.get(rs.getString("created_by")));
            exhibitor.setCreatedAt(rs.getTimestamp("created_at"));
            exhibitor.setUpdatedBy(userMap_o_id_2_n_obj.get(rs.getString("updated_by")));
            exhibitor.setUpdatedAt(rs.getTimestamp("updated_at"));
            exhibitor.setExhibition(exhibitionMap_o_id_2_n_obj.get(rs.getString("exhibition")));

            exhibitor.setName(rs.getString("name"));
            exhibitor.setStand(rs.getString("stand"));
            exhibitor.setCompany(rs.getString("company"));
            exhibitor.setEmail(rs.getString("email"));
            exhibitor.setAddress(rs.getString("address"));
            exhibitor.setWebsite(rs.getString("website"));
            exhibitor.setCategory(rs.getString("category"));
            exhibitor.setLocation(rs.getString("location"));
            exhibitor.setPhone(rs.getString("phone"));
            exhibitor.setDescription(rs.getString("description"));

            exhibitionService.save(exhibitor);
        }
        rs.close();
    }

    private void doSpeaker() throws Exception {
        dropCollectionsWithSuffix(new Speaker().getCollectionSuffix());
        ResultSet rs = st.executeQuery("SELECT * FROM speaker");
        while (rs.next()) {
            Speaker speaker = new Speaker();
            speaker.setCreatedBy(userMap_o_id_2_n_obj.get(rs.getString("created_by")));
            speaker.setCreatedAt(rs.getTimestamp("created_at"));
            speaker.setUpdatedBy(userMap_o_id_2_n_obj.get(rs.getString("updated_by")));
            speaker.setUpdatedAt(rs.getTimestamp("updated_at"));
            speaker.setExhibition(exhibitionMap_o_id_2_n_obj.get(rs.getString("exhibition")));

            speaker.setName(rs.getString("name"));
            speaker.setProfile(rs.getString("profile"));
            speaker.setEmail(rs.getString("email"));
            speaker.setPosition(rs.getString("position"));
            speaker.setCompany(rs.getString("company"));
            speaker.setMobilePhone(rs.getString("mobile_phone"));
            speaker.setPhoto(rs.getString("photo"));

            exhibitionService.save(speaker);
        }
        rs.close();
    }

    private void doSponsor() throws Exception {
        dropCollectionsWithSuffix(new Sponsor().getCollectionSuffix());
        ResultSet rs = st.executeQuery("SELECT * FROM sponsor");
        while (rs.next()) {
            Sponsor sponsor = new Sponsor();
            sponsor.setCreatedBy(userMap_o_id_2_n_obj.get(rs.getString("created_by")));
            sponsor.setCreatedAt(rs.getTimestamp("created_at"));
            sponsor.setUpdatedBy(userMap_o_id_2_n_obj.get(rs.getString("updated_by")));
            sponsor.setUpdatedAt(rs.getTimestamp("updated_at"));
            sponsor.setExhibition(exhibitionMap_o_id_2_n_obj.get(rs.getString("exhibition")));

            sponsor.setName(rs.getString("name"));
            sponsor.setBannerImage(rs.getString("banner_image"));
            sponsor.setWebsite(rs.getString("website"));

            exhibitionService.save(sponsor);
        }
        rs.close();
    }

    private void doPushMessage() throws Exception {
        dropCollectionsWithSuffix(new PushMessage().getCollectionSuffix());
        ResultSet rs = st.executeQuery("SELECT * FROM push_message");
        while (rs.next()) {
            PushMessage pushMessage = new PushMessage();
            pushMessage.setExhibition(exhibitionMap_o_id_2_n_obj.get(rs.getString("exhibition")));

            pushMessage.setTitle(rs.getString("title"));
            pushMessage.setBody(rs.getString("body"));
            pushMessage.setRecipients(rs.getString("recipients"));
            pushMessage.setDeliverDate(rs.getTimestamp("deliver_date"));

            exhibitionService.save(pushMessage);
        }
        rs.close();
    }

    private void doAttendee() throws Exception {
        dropCollectionsWithSuffix(new Attendee().getCollectionSuffix());
        ResultSet rs = st.executeQuery("SELECT * FROM attendee");
        while (rs.next()) {
            Attendee attendee = new Attendee();
            attendee.setRegisterDate(rs.getTimestamp("register_date"));
            attendee.setServiceToken(rs.getString("service_token"));
            if ("IOS".equals(rs.getString("mobile_platform"))) {
                attendee.setMobilePlatform(MobilePlatform.IOS);
            } else {
                attendee.setMobilePlatform(MobilePlatform.ANDROID);
            }
            attendee.setExhibition(exhibitionMap_o_id_2_n_obj.get(rs.getString("exhibition")));
            //history
            ResultSet rsCheckIn = conn.createStatement().executeQuery("SELECT * FROM check_in_entry where attendee=" + rs.getString("id"));
            while (rsCheckIn.next()) {
                CheckInEntry checkInEntry = new CheckInEntry();
                attendee.getCheckInHistories().add(checkInEntry);
                checkInEntry.setAttendee(attendee);
                checkInEntry.setLocation(new Location(rsCheckIn.getDouble("longitude"), rsCheckIn.getDouble("latitude"), rsCheckIn.getString("address")));
                checkInEntry.setDate(rsCheckIn.getTimestamp("date"));
            }
            rsCheckIn.close();
            attendee.setLocation(new Location(rs.getDouble("longitude"), rs.getDouble("latitude"), rs.getString("address")));
            attendee.setDistance(rs.getInt("distance"));
            attendee.setCheckInAt(rs.getTimestamp("check_in_at"));

            exhibitionService.save(attendee);
        }
        rs.close();
    }
}
