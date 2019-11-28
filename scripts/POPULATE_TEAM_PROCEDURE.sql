create or replace PROCEDURE POPULATE_TEAM AS  BEGIN
DECLARE
   a NUMBER(10) := 0;

BEGIN
  --POPULATES 900000 TEAMS
  WHILE a < 900000 LOOP
      INSERT INTO TEAM
      (ID, NAME, FUNDS)
      VALUES
      (LOWER(REGEXP_REPLACE(sys_guid(), '(........)(....)(....)(....)(............)','\1-\2-\3-\4-\5')),
      CONCAT('Team ',CAST(a AS VARCHAR(100))),
      dbms_random.value(1,99999));

     a := a + 1;
  END LOOP;
END;
END POPULATE_TEAM;
