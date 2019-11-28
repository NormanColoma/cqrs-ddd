CREATE TABLE "SETSDEV"."PLAYER"
   (	"ID" CHAR(36) NOT NULL ENABLE,
	"NAME" VARCHAR2(100),
	"DORSAL" NUMBER(2,0),
	"PRICE" NUMBER(7,2),
	"TEAM_ID" CHAR(36),
	 CONSTRAINT "PLAYER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE,
	 CONSTRAINT "TEAM_PLAYER" FOREIGN KEY ("TEAM_ID")
	  REFERENCES "SETSDEV"."TEAM" ("ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM";
