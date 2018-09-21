# git

* 폴더 위치로 이동(cd 명령어로)
* 명령어들
  * git init
    * 이를 통해  git을 사용할 폴더를 정할 수 있음.
  * git add *
    * 올리기를 원하는 파일들을 저장하는 작업
  * git commit -m "day5"
    * 설명을 추가 하면서 올릴 파일들을 중간 저장지로 보내는 작업 
  * git remote add origin [github 주소]
    * 이를 이용해 내 github에 파일들을 업로드 가능함.
  * git remote -v
    * 이때까지 저장된 저장지 주소들 확인.
  * git push -u origin master
    * 하면 중간 저장지의 파일들이 보내짐
  * git status
    * git add 된 파일 상태 확인
  * git reset
    * git add 된 파일 전체 삭제
  * git reset HEAD
    * git commit 된 파일 전체 삭제
  * git diff --stat --cached [remote/branch]
    * Push하지 않은 파일 목록 확인
    * 예) git diff --stat --cached origin/master
  * git log [since]..[until]
    * Push하지 않은 Commit 확인
    * 예) git log origin/master..master



# Trello

* 프로젝트 관리 사이트



# slack

* 코드 보내기 편한 사이트