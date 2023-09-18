-- DROP TABLE public.restaurants;

CREATE TABLE public.restaurants (
	id int8 NOT NULL,
	"name" varchar NULL,
	CONSTRAINT restaurants_pkey PRIMARY KEY (id)
);