## Introduction
In this document we'll cover how to reproduce the database that drives the news article backend

## Prerequisites
- postgresql 9.4.x
- python 3.4.x
- pip3
- Psycopg2
- A copy of the dataset
- sed
- sudo
- su

## Resources
- Parent document to dataset: 
https://data.gov.au/dataset/abc-news-may-2016

- Direct link to abc news metadata:
https://data.gov.au/dataset/abc-news-may-2016/resource/99f689a1-1468-4410-9779-edc347e1d927

## Proceedure
So due to some articles containing quoted strings or just standalone quotes there are a handful of articles in the metadata dataset that will break the postgres COPY SQL command so we'll need to clean it up first before we can actually import it

1. Download a copy of the dataset to your current user
2. Copy the ABCnewsmetadata.csv to /tmp
3. Impersonate 'postgres' user, to do this, type `sudo su postgres`
4. Create the database `createdb govhack2016`
5. Init the database structure `cat db.sql > psql govhack2016`
6. Run the following commands to clean up the data, you will need to do this **every** time, at time of writing there is about 36 articles in the dataset that are malformed. Replace 12345 with the offending line number when postgres complains.
  `sed '12345d' /tmp/ABCnewsmetadata.sedded.csv > /tmp/ABCnewsmetadata.sedded.csv.tmp; mv /tmp/ABCnewsmetadata.sedded.csv.tmp /tmp/ABCnewsmetadata.sedded.csv; cat import_data.sql | psql govhack2016`
7. Once it finally imports *something*
