------------------------------
-- pgDesigner 1.2.17
--
-- Project    : smprojectdb
-- Date       : 09/20/2011 02:40:51.527
-- Description: 
------------------------------


-- Start Domain's declaration
DROP DOMAIN IF EXISTS "smartproject" CASCADE;
CREATE DOMAIN "smartproject" AS character varying NOT NULL;

-- End Domain's declaration

-- Start Table's declaration
DROP TABLE IF EXISTS "project" CASCADE;
CREATE TABLE "project" (
"id" serial NOT NULL,
"name" varchar NOT NULL,
"code" varchar NOT NULL,
"create_dt" timestamptz NOT NULL,
"end_dt" timestamptz,
"methodology" serial,
"state" integer NOT NULL
) WITH OIDS;
ALTER TABLE "project" ADD CONSTRAINT "project_pk" PRIMARY KEY("id");
COMMENT ON TABLE "project" IS 'Projects Table';
COMMENT ON COLUMN "project"."id" IS 'Identity';
COMMENT ON COLUMN "project"."name" IS 'Nome do Projeto';
COMMENT ON COLUMN "project"."code" IS 'Código do Projeto';
COMMENT ON COLUMN "project"."create_dt" IS 'Data de Criação do Projeto';
COMMENT ON COLUMN "project"."end_dt" IS 'Data da Última Atualização do Projeto';
COMMENT ON COLUMN "project"."methodology" IS 'Choosed Methodology for this project';
COMMENT ON COLUMN "project"."state" IS 'Project State';
CREATE UNIQUE INDEX "project_idx1" ON "project" USING btree ("id","name","code");

DROP TABLE IF EXISTS "methodology" CASCADE;
CREATE TABLE "methodology" (
"id" serial NOT NULL,
"name" varchar NOT NULL
) WITH OIDS;
ALTER TABLE "methodology" ADD CONSTRAINT "methodology_pk" PRIMARY KEY("id");
COMMENT ON COLUMN "methodology"."id" IS 'Identity';
COMMENT ON COLUMN "methodology"."name" IS 'Methodology Name';

DROP TABLE IF EXISTS "users" CASCADE;
CREATE TABLE "users" (
"id" serial NOT NULL,
"login" character varying(20) NOT NULL,
"email" varchar(50) NOT NULL,
"password" varchar(50) NOT NULL,
"address" integer,
"current_api_key" varchar(20),
"credential" integer NOT NULL
) WITH OIDS;
ALTER TABLE "users" ADD CONSTRAINT "users_pk" PRIMARY KEY("id");
COMMENT ON TABLE "users" IS 'Users DataTables';
COMMENT ON COLUMN "users"."id" IS 'Identity';
COMMENT ON COLUMN "users"."login" IS 'Login username for this user';
COMMENT ON COLUMN "users"."email" IS 'Email';
COMMENT ON COLUMN "users"."password" IS 'Coded User Password';
COMMENT ON COLUMN "users"."address" IS 'User Address';
COMMENT ON COLUMN "users"."current_api_key" IS 'Current API key';
COMMENT ON COLUMN "users"."credential" IS 'User Credential';

DROP TABLE IF EXISTS "proj_states" CASCADE;
CREATE TABLE "proj_states" (
"id" serial NOT NULL,
"state" varchar(20) NOT NULL
) WITH OIDS;
ALTER TABLE "proj_states" ADD CONSTRAINT "proj_states_pk" PRIMARY KEY("id");
COMMENT ON COLUMN "proj_states"."state" IS 'Estado do projeto';

DROP TABLE IF EXISTS "tasks" CASCADE;
CREATE TABLE "tasks" (
"id" serial NOT NULL,
"name" varchar(50) NOT NULL
) WITH OIDS;
ALTER TABLE "tasks" ADD CONSTRAINT "tasks_pk" PRIMARY KEY("id");
COMMENT ON COLUMN "tasks"."id" IS 'Identity';
COMMENT ON COLUMN "tasks"."name" IS 'Task Name';

DROP TABLE IF EXISTS "address" CASCADE;
CREATE TABLE "address" (
"id" serial NOT NULL,
"street" character varying(100) NOT NULL,
"zipcode" varchar(10) NOT NULL,
"complement" varchar(100),
"city" varchar(50) NOT NULL,
"state" character varying(50) NOT NULL,
"country" varchar(50) NOT NULL
) WITH OIDS;
ALTER TABLE "address" ADD CONSTRAINT "address_pk" PRIMARY KEY("id");
COMMENT ON COLUMN "address"."id" IS 'Identity';
COMMENT ON COLUMN "address"."complement" IS 'Address Complement';
COMMENT ON COLUMN "address"."city" IS 'Address City';
COMMENT ON COLUMN "address"."state" IS 'Address State';
COMMENT ON COLUMN "address"."country" IS 'Country';

DROP TABLE IF EXISTS "project_tasks" CASCADE;
CREATE TABLE "project_tasks" (
"id" serial NOT NULL,
"project" integer NOT NULL,
"task" integer NOT NULL
) WITH OIDS;
ALTER TABLE "project_tasks" ADD CONSTRAINT "project_tasks_pk" PRIMARY KEY("id","project","task");
COMMENT ON TABLE "project_tasks" IS 'Project and Tasks';
COMMENT ON COLUMN "project_tasks"."id" IS 'Identity';
COMMENT ON COLUMN "project_tasks"."project" IS 'Project Key';
COMMENT ON COLUMN "project_tasks"."task" IS 'Task Key';

DROP TABLE IF EXISTS "subtasks" CASCADE;
CREATE TABLE "subtasks" (
"id" integer NOT NULL,
"name" varchar(50) NOT NULL
) WITH OIDS;
ALTER TABLE "subtasks" ADD CONSTRAINT "subtasks_pk" PRIMARY KEY("id");
COMMENT ON COLUMN "subtasks"."id" IS 'Identity';
COMMENT ON COLUMN "subtasks"."name" IS 'Subtask Name';

-- End Table's declaration

-- Start Relation's declaration
ALTER TABLE "project" DROP CONSTRAINT "project_methodology_fk" CASCADE;
ALTER TABLE "project" ADD CONSTRAINT "project_methodology_fk" FOREIGN KEY ("methodology") REFERENCES "methodology"("id") ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE "project" DROP CONSTRAINT "project_states_fk" CASCADE;
ALTER TABLE "project" ADD CONSTRAINT "project_states_fk" FOREIGN KEY ("state") REFERENCES "proj_states"("id") ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE "users" DROP CONSTRAINT "users_address_fk" CASCADE;
ALTER TABLE "users" ADD CONSTRAINT "users_address_fk" FOREIGN KEY ("address") REFERENCES "address"("id") ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE "project_tasks" DROP CONSTRAINT "project_tasks_tasks_fk" CASCADE;
ALTER TABLE "project_tasks" ADD CONSTRAINT "project_tasks_tasks_fk" FOREIGN KEY ("task") REFERENCES "tasks"("id") ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE "project_tasks" DROP CONSTRAINT "project_tasks_project_fk" CASCADE;
ALTER TABLE "project_tasks" ADD CONSTRAINT "project_tasks_project_fk" FOREIGN KEY ("project") REFERENCES "project"("id") ON UPDATE RESTRICT ON DELETE RESTRICT;

-- End Relation's declaration

