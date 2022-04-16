# 숫자 야구 게임
## 진행 방법
* 숫자 야구 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

## 과제 제출 과정
* [과제 제출 방법](https://github.com/next-step/nextstep-docs/tree/master/precourse)

## 기능 정의

- [x] 볼/볼들을 구현한다.
  - 볼은 1~9 사이의 숫자를 가진다.
  - 볼들은 3자리의 수로 이루어진다.
  - 볼들 사이에서 동일한 볼의 위치를 알 수 있어야한다. 
- [x] 규칙/결과를 구현한다. 
  - 스트라이크
  - 볼
  - 낫싱
- [x] 플레이어를 구현한다.
  - 입력(System.in)
  - 컴퓨터(랜덤)
- [ ] 전체 야구 게임 흐름을 구현한다.
  - 3스타라이크가 나오면 게임을 종료한다.
  - 게임 종료 후, 재시도 여부에 따라 다시 시작하거나 완전히 종료한다.
  - 잘 못된 값을 입력한 경우, 예외를 발생시키고 게임을 완전히 종료시킨다.
