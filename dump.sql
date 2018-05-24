--
-- PostgreSQL database dump
--

-- Dumped from database version 10.4 (Ubuntu 10.4-1.pgdg16.04+1)
-- Dumped by pg_dump version 10.4 (Ubuntu 10.4-1.pgdg16.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: advice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.advice (
    id integer NOT NULL,
    teacher_id integer NOT NULL,
    start_date timestamp without time zone NOT NULL,
    end_date timestamp without time zone NOT NULL,
    duration_per_student integer DEFAULT 0,
    type integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.advice OWNER TO postgres;

--
-- Name: advice_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.advice_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.advice_id_seq OWNER TO postgres;

--
-- Name: advice_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.advice_id_seq OWNED BY public.advice.id;


--
-- Name: group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."group" (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public."group" OWNER TO postgres;

--
-- Name: group_advice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.group_advice (
    id integer NOT NULL,
    group_id integer NOT NULL,
    advice_id integer,
    descriotion character(1)
);


ALTER TABLE public.group_advice OWNER TO postgres;

--
-- Name: group_advice_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.group_advice_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.group_advice_id_seq OWNER TO postgres;

--
-- Name: group_advice_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.group_advice_id_seq OWNED BY public.group_advice.id;


--
-- Name: group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.group_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.group_id_seq OWNER TO postgres;

--
-- Name: group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.group_id_seq OWNED BY public."group".id;


--
-- Name: group_student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.group_student (
    id integer NOT NULL,
    student_id integer NOT NULL,
    group_id integer NOT NULL
);


ALTER TABLE public.group_to_student OWNER TO postgres;

--
-- Name: group_student_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.group_student_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.group_student_id_seq OWNER TO postgres;

--
-- Name: group_student_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.group_student_id_seq OWNED BY public.group_student.id;


--
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    hash character varying(255) NOT NULL
);


ALTER TABLE public.student OWNER TO postgres;

--
-- Name: student_advice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student_advice (
    id integer NOT NULL,
    student_id integer NOT NULL,
    advice_id integer NOT NULL,
    reserved_start_date timestamp without time zone NOT NULL,
    reserved_end_date timestamp without time zone,
    actual_start_date timestamp without time zone,
    actual_end_date timestamp without time zone,
    desctiption character(1)
);


ALTER TABLE public.student_advice OWNER TO postgres;

--
-- Name: student_advice_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.student_advice_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.student_advice_id_seq OWNER TO postgres;

--
-- Name: student_advice_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.student_advice_id_seq OWNED BY public.student_advice.id;


--
-- Name: student_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.student_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.student_id_seq OWNER TO postgres;

--
-- Name: student_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.student_id_seq OWNED BY public.student.id;


--
-- Name: teacher; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.teacher (
    id integer NOT NULL,
    email character varying(250) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    hash character varying(255) NOT NULL
);


ALTER TABLE public.teacher OWNER TO postgres;

--
-- Name: teacher_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.teacher_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.teacher_id_seq OWNER TO postgres;

--
-- Name: teacher_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.teacher_id_seq OWNED BY public.teacher.id;


--
-- Name: advice id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advice ALTER COLUMN id SET DEFAULT nextval('public.advice_id_seq'::regclass);


--
-- Name: group id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."group" ALTER COLUMN id SET DEFAULT nextval('public.group_id_seq'::regclass);


--
-- Name: group_advice id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_advice ALTER COLUMN id SET DEFAULT nextval('public.group_advice_id_seq'::regclass);


--
-- Name: group_student id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_student ALTER COLUMN id SET DEFAULT nextval('public.group_student_id_seq'::regclass);


--
-- Name: student id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student ALTER COLUMN id SET DEFAULT nextval('public.student_id_seq'::regclass);


--
-- Name: student_advice id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_advice ALTER COLUMN id SET DEFAULT nextval('public.student_advice_id_seq'::regclass);


--
-- Name: teacher id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher ALTER COLUMN id SET DEFAULT nextval('public.teacher_id_seq'::regclass);


--
-- Data for Name: advice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.advice (id, teacher_id, start_date, end_date, duration_per_student, type) FROM stdin;
\.


--
-- Data for Name: group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."group" (id, name) FROM stdin;
\.


--
-- Data for Name: group_advice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.group_advice (id, group_id, advice_id, descriotion) FROM stdin;
\.


--
-- Data for Name: group_student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.group_student (id, student_id, group_id) FROM stdin;
\.


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (id, email, first_name, last_name, hash) FROM stdin;
\.


--
-- Data for Name: student_advice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student_advice (id, student_id, advice_id, reserved_start_date, reserved_end_date, actual_start_date, actual_end_date, desctiption) FROM stdin;
\.


--
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.teacher (id, email, first_name, last_name, hash) FROM stdin;
\.


--
-- Name: advice_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.advice_id_seq', 1, false);


--
-- Name: group_advice_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.group_advice_id_seq', 1, false);


--
-- Name: group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.group_id_seq', 1, false);


--
-- Name: group_student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.group_student_id_seq', 1, false);


--
-- Name: student_advice_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.student_advice_id_seq', 1, false);


--
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.student_id_seq', 1, false);


--
-- Name: teacher_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.teacher_id_seq', 1, false);


--
-- Name: advice advice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advice
    ADD CONSTRAINT advice_pkey PRIMARY KEY (id);


--
-- Name: group_advice group_advice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_advice
    ADD CONSTRAINT group_advice_pkey PRIMARY KEY (id);


--
-- Name: group group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."group"
    ADD CONSTRAINT group_pkey PRIMARY KEY (id);


--
-- Name: group_student group_student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_student
    ADD CONSTRAINT group_student_pkey PRIMARY KEY (id);


--
-- Name: student_advice student_advice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_advice
    ADD CONSTRAINT student_advice_pkey PRIMARY KEY (id);


--
-- Name: teacher teacher_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (id);


--
-- Name: advice_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX advice_id_uindex ON public.advice USING btree (id);


--
-- Name: group_advice_group_id_advice_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX group_advice_group_id_advice_id_uindex ON public.group_advice USING btree (group_id, advice_id);


--
-- Name: group_advice_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX group_advice_id_uindex ON public.group_advice USING btree (id);


--
-- Name: group_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX group_id_uindex ON public."group" USING btree (id);


--
-- Name: group_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX group_name_uindex ON public."group" USING btree (name);


--
-- Name: group_student_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX group_student_id_uindex ON public.group_student USING btree (id);


--
-- Name: group_student_student_id_group_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX group_student_student_id_group_id_uindex ON public.group_student USING btree (student_id, group_id);


--
-- Name: student_advice_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX student_advice_id_uindex ON public.student_advice USING btree (id);


--
-- Name: student_advice_student_id_advice_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX student_advice_student_id_advice_id_uindex ON public.student_advice USING btree (student_id, advice_id);


--
-- Name: student_email_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX student_email_uindex ON public.student USING btree (email);


--
-- Name: student_hash_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX student_hash_uindex ON public.student USING btree (hash);


--
-- Name: student_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX student_id_uindex ON public.student USING btree (id);


--
-- Name: teacher_email_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX teacher_email_uindex ON public.teacher USING btree (email);


--
-- Name: teacher_hash_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX teacher_hash_uindex ON public.teacher USING btree (hash);


--
-- Name: teacher_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX teacher_id_uindex ON public.teacher USING btree (id);


--
-- Name: advice advice_teacher_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.advice
    ADD CONSTRAINT advice_teacher_id_fk FOREIGN KEY (teacher_id) REFERENCES public.teacher(id);


--
-- Name: group_advice group_advice_advice_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_advice
    ADD CONSTRAINT group_advice_advice_id_fk FOREIGN KEY (advice_id) REFERENCES public.advice(id);


--
-- Name: group_advice group_advice_group_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_advice
    ADD CONSTRAINT group_advice_group_id_fk FOREIGN KEY (group_id) REFERENCES public."group"(id);


--
-- Name: group_student group_student_group_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_student
    ADD CONSTRAINT group_student_group_id_fk FOREIGN KEY (group_id) REFERENCES public."group"(id) ON DELETE CASCADE;


--
-- Name: group_student group_student_student_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_student
    ADD CONSTRAINT group_student_student_id_fk FOREIGN KEY (student_id) REFERENCES public.student(id) ON DELETE CASCADE;


--
-- Name: student_advice student_advice_advice_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_advice
    ADD CONSTRAINT student_advice_advice_id_fk FOREIGN KEY (advice_id) REFERENCES public.advice(id);


--
-- Name: student_advice student_advice_student_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_advice
    ADD CONSTRAINT student_advice_student_id_fk FOREIGN KEY (student_id) REFERENCES public.student(id);


--
-- PostgreSQL database dump complete
--

