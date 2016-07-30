--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: article_meta; Type: TABLE; Schema: public; Owner: max; Tablespace:
--

CREATE TABLE article_meta (
    contentid integer,
    contenttype text,
    contenttitletag text,
    contentheadline text,
    contentkeywords text,
    contentlatitude text,
    contentlongitude text,
    contentnewsfromothersites text,
    contentcomments text,
    contentlength text,
    contentparagraphs text,
    contentwords text,
    contentduration text,
    contentvideos text,
    contentaudio text,
    contentphotos text,
    contentgalleries text,
    contentmaps text,
    contentrelatedstories text,
    contentfirstpublished bigint,
    contentdescription text,
    contenttopics text
);


ALTER TABLE article_meta OWNER TO postgres;

--
-- Name: article_meta_raw; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE article_meta_raw (
    contentid text,
    contenttype text,
    contenttitletag text,
    contentheadline text,
    contentkeywords text,
    contentlatitude text,
    contentlongitude text,
    contentnewsfromothersites text,
    contentcomments text,
    contentlength text,
    contentparagraphs text,
    contentwords text,
    contentduration text,
    contentvideos text,
    contentaudio text,
    contentphotos text,
    contentgalleries text,
    contentmaps text,
    contentrelatedstories text,
    contentfirstpublished text,
    contentdescription text,
    contenttopics text
);


ALTER TABLE article_meta_raw OWNER TO postgres;

--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
