# call-center-service
It is a Spring-boot project to calculate analytics from the call data saved for a callcenter.

# Java 11

mvn clean install

Curls for Postman Collections
-------------------------------

1. Import Data

curl --location 'localhost:9191/analytics/import' \
--header 'Content-Type: application/json' \
--data '{
"fromNumber": "9999900000",
"startTime": "2021-01-13T06:00:05",
"endTime": "2021-01-13T06:23:06",
"duration": 181
}'

2. Import List of data

curl --location 'localhost:9191/analytics/importList' \
--header 'Content-Type: application/json' \
--data '[
{
"fromNumber": "9999902010",
"startTime": "2021-01-13T06:12:49",
"endTime": "2021-01-13T06:14:44",
"duration": 115
},
{
"fromNumber": "9991323232",
"startTime": "2022-01-13T07:02:49",
"endTime": "2022-01-13T07:14:44",
"duration": 715
},
{
"fromNumber": "8484848484",
"startTime": "2021-01-13T08:12:49",
"endTime": "2021-01-13T08:14:44",
"duration": 115
},
{
"fromNumber": "9378373737",
"startTime": "2021-01-13T10:12:49",
"endTime": "2021-01-13T10:14:44",
"duration": 115
},
{
"fromNumber": "9837373737",
"startTime": "2021-01-13T12:12:49",
"endTime": "2021-01-13T12:14:44",
"duration": 115
},
{
"fromNumber": "9973737373",
"startTime": "2021-01-13T15:12:49",
"endTime": "2021-01-13T15:14:44",
"duration": 115
}
]'

3. Hour of the day when the call volume is highest.

curl --location 'localhost:9191/analytics/callWithHighestVolume'

4. Hour of the day when the calls are longest.

curl --location 'localhost:9191/analytics/longestCallByHour'

5. Day of the week when the call volume is highest.

curl --location 'localhost:9191/analytics/highestCallByDayOfWeek'

6. Day of the week when the calls are longest.

curl --location 'localhost:9191/analytics/longestCallByDayOfWeek'

