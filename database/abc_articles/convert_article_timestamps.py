#!/usr/bin/python
#
# Small script to show PostgreSQL and Pyscopg together
#

import psycopg2
import datetime

try:
    conn = psycopg2.connect("dbname='govhack2016' user='postgres' password='password' host='localhost' port='5432'")
except:
    print("I am unable to connect to the database")

cur = conn.cursor()
cur2 = conn.cursor()

sql = "SELECT contentid,contenttype,contenttitletag,contentheadline,contentkeywords,contentlatitude,contentlongitude,"
sql += "contentnewsfromothersites,contentcomments,contentlength,contentparagraphs,contentwords,"
sql += "contentduration,contentvideos,contentaudio,contentphotos,contentgalleries,contentmaps,"
sql += "contentrelatedstories,contentfirstpublished,contentdescription,contenttopics "
sql += "FROM article_meta_raw LIMIT 100"


cur.execute(sql)

row = cur.fetchone()
while row:
	# contentid,contenttype,contenttitletag,contentheadline,contentkeywords,contentlatitude,contentlongitude,
	# contentnewsfromothersites,contentcomments,contentlength,contentparagraphs,contentwords,
	# contentduration,contentvideos,contentaudio,contentphotos,contentgalleries,contentmaps,
	# contentrelatedstories,contentfirstpublished,contentdescription,contenttopics
    print ("Processing",row[0]," - ",row[19])

    dt_raw = row[19]
    out_dt = int(datetime.datetime.strptime(dt_raw,"%Y-%m-%d %H:%M:%S").strftime("%s")) * 1000

    rowa = (
    		row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],
    		row[10],row[11],row[12],row[13],row[14],row[15],row[16],row[17],row[18],out_dt,
    		row[20],row[21]
    		)

    sql = "INSERT INTO article_meta ("
    sql += "contentid,contenttype,contenttitletag,contentheadline,contentkeywords,contentlatitude,contentlongitude,"
    sql += "contentnewsfromothersites,contentcomments,contentlength,contentparagraphs,contentwords,"
    sql += "contentduration,contentvideos,contentaudio,contentphotos,contentgalleries,contentmaps,"
    sql += "contentrelatedstories,contentfirstpublished,contentdescription,contenttopics"
    sql += ") VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);"

    
    cur2.execute(sql,rowa)

    row = cur.fetchone()

conn.commit()