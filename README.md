

## edge-service

**Description**

* Does authorization
* proxies every request (do whitelist instead of blacklist)
    * except *     /streams/download
    * except *     /media/convert
    * except POST  /downloads
    * except POST  /files

**API**

* POST /login

**Technology**

Language: Java  
Framework: Spring Boot

## user-service

**API**

* POST /users/register
* GET  /users/account
* PUT  /users/account

**Technology**

Language: Java  
Framework: Spring Boot
Database: MongoDB

## download-mediation-service

**API**

* POST /download
    * Calls download-status-service, (AMQP or async: stream-service, media-conversion-service, file-service)

**Technology**

Language: NodeJS / Typescript

## stream-service

**API**

* GET  /streams
* POST /streams/get-info
* POST /internal/streams/download (called upstream) - 64MB limit 

**Critical Specs**

* cpu?
* memory? 
* disk 

**Technology**

Language: NodeJS / Typescript

## media-conversion-service 
 
**API**
 
* GET  /media 
* POST /internal/media/convert (called upstream)

**Critical Specs**

* cpu
* memory? 
* disk (for ffmpeg)

**Technology**

Language: NodeJS / Typescript

## download-status-service 

**API**

* POST /internal/downloads (called upstream)
* GET  /downloads/status
* GET  /downloads?before={id}&count=25
* GET  /downloads/{id}

**Critical Specs**

* cpu?
* db cpu (and memory?)

**Technology**

Language: NodeJS / Typescript
Database: MongoDB

## file-service

**API**

* POST /internal/files (called upstream)
* GET  /files?before={id}&count=50&sort=title.asc,genre.desc
* GET  /files/{id}
* GET  /files/{id}/content

**Critical Specs**

* memory? 
* db disk

**Technology**

Language: NodeJS / Typescript
Database: MongoDB

## keyword-service

**API**

* GET  /keywords?artist=KSE
* PUT  /keywords (called upstream)

**Technology**

Language: NodeJS / Typescript
Database: MongoDB

## queue-service

**API**

* POST /queue
* GET  /queue/{id} 
* POST /queue/{id}/next
* POST /queue/{id}/previous

**Technology**

Language: NodeJS / Typescript
Database: MongoDB

**Notes**

* Same data store as file-service?