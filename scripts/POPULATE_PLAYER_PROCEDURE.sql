create or replace PROCEDURE POPULATE_PLAYER AS  BEGIN
DECLARE
    off_set NUMBER(9) := 0;
    dorsal NUMBER(2,0) := 0;
    a INTEGER := 0;
    team_id char(36);
    i INTEGER := 0;

BEGIN
    --POPULATES 3 PLAYERS PER TEAM
    DECLARE
      CURSOR cteam IS
      SELECT id FROM TEAM WHERE ID NOT IN (SELECT DISTINCT TEAM_ID FROM PLAYER);
      team_id CHAR(36);
    BEGIN
      OPEN cteam;
      LOOP
          FETCH cteam INTO team_id;
            BEGIN
                EXIT WHEN cteam%NOTFOUND;

                i := 0;
                WHILE (i < 3)
                LOOP
                    INSERT INTO PLAYER (ID, NAME, DORSAL, PRICE, TEAM_ID) VALUES
                    (
                    LOWER(REGEXP_REPLACE(sys_guid(), '(........)(....)(....)(....)(............)','\1-\2-\3-\4-\5')),
                    CONCAT('Player ',i),
                    i,
                    dbms_random.value(1,99), team_id
                    );

                    i := i + 1;
                END LOOP;

                DBMS_OUTPUT.PUT_LINE(team_id);
                COMMIT;
            END;
      END LOOP;
      CLOSE cteam;
    END;

    /*
    WHILE a < 5000 LOOP
        dorsal := dbms_random.value(1,10);

        off_set := dbms_random.value(1,60000);
        SELECT id INTO team_id FROM cached_lookup_tab WHERE NUMERO = off_set;

        INSERT INTO PLAYER (ID, NAME, DORSAL, PRICE, TEAM_ID)
        VALUES
        (
        LOWER(REGEXP_REPLACE(sys_guid(), '(........)(....)(....)(....)(............)','\1-\2-\3-\4-\5')),
        CONCAT('Player ',CAST(a AS VARCHAR(100))),
        dorsal,
        dbms_random.value(1,99),
        team_id
        );

        a := a + 1;
    END LOOP;
    */
END;
END POPULATE_PLAYER;
