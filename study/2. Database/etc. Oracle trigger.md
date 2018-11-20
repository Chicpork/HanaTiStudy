# Oracle Trigger

``` sql
-- tbl_reply에 insert 될 때 전체 개수를 한개 올려주는 트리거
CREATE OR replace TRIGGER triger_tbl_board_replycnt_up 
  AFTER INSERT ON tbl_reply 
  FOR EACH ROW 
BEGIN 
    UPDATE tbl_board 
    SET    replycnt = replycnt + 1 
    WHERE  bno = :new.bno; 
END; 
/

-- tbl_reply에 insert 될 때 전체 개수를 한개 내려주는 트리거
CREATE OR replace TRIGGER triger_tbl_board_replycnt_down 
  AFTER DELETE ON tbl_reply 
  FOR EACH ROW 
BEGIN 
    UPDATE tbl_board 
    SET    replycnt = replycnt - 1 
    WHERE  bno = :old.bno; 
END; 
/ 
```

