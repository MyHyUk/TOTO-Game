CREATE or REPLACE PROCEDURE reset_sequence (sequencename IN VARCHAR2) as
curr_val INTEGER;
BEGIN
EXECUTE IMMEDIATE 'alter sequence ' ||sequencename||' MINVALUE 0';
EXECUTE IMMEDIATE 'SELECT ' ||sequencename ||'.nextval FROM dual' INTO curr_val;
EXECUTE IMMEDIATE 'alter sequence ' ||sequencename||' increment by -'||curr_val;
EXECUTE IMMEDIATE 'SELECT ' ||sequencename ||'.nextval FROM dual' INTO curr_val;
EXECUTE IMMEDIATE 'alter sequence ' ||sequencename||' increment by 1';
END reset_sequence;
/