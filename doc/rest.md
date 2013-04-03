# Exhibition RESTful Request

## Exhibition Data
### Request
`http://180.168.35.37:8080/exhibition/api/exhibitions/{exhibition_code}`
### Response
    {
        updatedAt: <long: datetime>,
        name: <string>,
        description: <string>,
        speakers: [
            {
                name: <string>,
                profile: <string>,
                position: <string>,
                company: <string>,
                photo: <string: image url, relative location>
            },
            ...
        ],
        exhibitors: [
            {
                compnay: <string>,
                address: <string>,
                website: <string>,
                location: <string>,
                phone: <string>,
                description: <string>
            },
            ...
        ],
        eventSchedules: [
            {
                name: <string>,
                description: <string>,
                place: <string>,
                dateFrom: <long: datetime>,
                dateTo: <long: datetime>
            },
            ...
        ]
    }
### Parameter
* `exhibition_code` Which Exhibition to retrieve.  
* `If-None-Match` Http request header argument, returns `Status: 304` if not changed.

### Example
* [http://180.168.35.37:8080/exhibition/api/exhibitions/CCBN](http://180.168.35.37:8080/exhibition/api/exhibitions/CCBN)
* `curl -i -H "If-None-Match: 1364808988328" http://180.168.35.37:8080/exhibition/api/exhibitions/CCBN`