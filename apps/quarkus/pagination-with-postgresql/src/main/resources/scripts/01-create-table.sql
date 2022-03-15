CREATE TABLE public.ctpm_act_eco (
	act_codigo character varying NOT NULL,
	act_rut character varying NOT NULL,
	act_dv character varying NULL,
	act_descripcion character varying NULL,
	CONSTRAINT ctpm_act_eco_pk PRIMARY KEY (act_codigo,act_rut)
);
