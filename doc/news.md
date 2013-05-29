# 新闻接口说明

## api
`http://180.168.35.37:8080/exhibition/api/web/CCBN/news`
### 参数
`size: int` 返回新闻条数，默认为`10`  
`from: long` 分页获取数据时第一条新闻的创建时间
### 实例
`http://180.168.35.37:8080/exhibition/api/web/CCBN/news?size=8&from=0`
返回8条最新新闻  

`http://180.168.35.37:8080/exhibition/api/web/CCBN/news?size=8&from=1369815697922`
返回8条新闻，新闻创建时间早于new Date(1369815697922)