create or replace PROCEDURE POPULATE_PLAYER AS  BEGIN
DECLARE
    off_set NUMBER(9) := 0;
    dorsal NUMBER(2,0) := 0;
    a INTEGER := 0;
    team_id char(36);
    i INTEGER := 0;

BEGIN
    --POPULATES N PLAYERS PER TEAM (change i inside while loop to modify the numbers of players to create)
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
                WHILE (i < 4500)
                LOOP
                    INSERT INTO PLAYER (ID, NAME, DORSAL, PRICE, TEAM_ID) VALUES
                    (
                    LOWER(REGEXP_REPLACE(sys_guid(), '(........)(....)(....)(....)(............)','\1-\2-\3-\4-\5')),
                    CONCAT('Player ',CAST(i AS VARCHAR(100))),
                    dbms_random.value(1,99), dbms_random.value(1,50), team_id
                    );

                    i := i + 1;
                END LOOP;

                DBMS_OUTPUT.PUT_LINE(team_id);
                COMMIT;
            END;
      END LOOP;
      CLOSE cteam;
    END;
END;
END POPULATE_PLAYER;
