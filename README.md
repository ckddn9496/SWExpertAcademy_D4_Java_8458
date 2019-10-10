# SWExpertAcademy_D4_Java_8458

## SW Expert Academy D4 8458. 원점으로 집합

### 1. 문제설명

출처: https://swexpertacademy.com/main/code/problem/problemList.do

input으로 `N`개의 점이 들어온다. 매번 상하좌우로 움직일 수 있고 `i`번째 움직임에 `i`번 움직여야 한다는 조건이 있다. 이때 모든 점을 `(0,0)`으로 이동시키고자 할때 최소 몇번의 움직임으로 모든 점을 원점에 모을 수 있는지 구하라. 만약 모을 수 없다면 `-1`을 출력하는 문제

 [입력]
> 첫 번째 줄에 테스트 케이스의 수 `T`가 주어진다.
> 각 테스트 케이스의 첫 번째 줄에는 하나의 정수 `N(1 ≤ N ≤ 10)`이 주어진다.
> 다음 `N`개의 줄의 `i`번째 줄에는 두 정수 `xi, yi(-10^9 ≤ xi, yi ≤ 10^9)`가 공백 하나로 구분되어 주어진다.
> 이는 `i`번째 점이 `(xi, yi)`에 위치함을 나타낸다.


[출력]
> 각 테스트 케이스마다 `#x`(`x`는 테스트케이스 번호를 의미하며 `1`부터 시작한다)를 출력하고,
> 각 테스트 케이스마다 최소 몇 번 움직여야 모든 점이 원점에 모이는 지 출력한다.
> 만약 모든 점을 원점으로 이동시킬 수 없으면, `-1`을 출력한다.

### 2. 풀이

한점이 `(x, y)`를 가질 때 원점까지 이동하는데 걸리는 최소 이동거리는 `x+y`이다. 원점에서 거리가 `1`차이 나는데 까지 걸리는 횟수를 알 수 있다면 그곳에서 같은 위치를 반복해서 이동함으로써 더 멀리있는 점이 원점으로 올때까지 대기 할 수 있다. 원점으로 부터의 거리를 담은 배열과 원점으로 부터 거리가`1`이내에 도달한 점인지 판단하는 불리언 배열을 선언하였고 매 이동때마다 이를 갱신, 검사하였다.

만약 모두 원점으로 부터 거리가 `1`인 바운드안에 들어왔다면 모든 거리가 `1`로 통일 되거나 `0`으로 통일 되어야만 모두를 원점에 모을 수 있다. 모두 같은 거리 값을 가진다면 그 값이 `0`일때는 이미 모여있기 때문에 현재 까지 진행한 횟수를 return한다. 값이 `1`이라면 가장 최근에 진행한 횟수가 홀수번째인지 짝수번째 인지 구별한다. 홀수일때 다음번은 짝수, 그 다음번이 홀수번 이동이므로 `2`번 더 수행할 때 원점에 모이게된다. 짝수일때 다음번이 홀수번 이동이므로 `1`번 더 수행할 때 원점에 모이게된다. 이를 추가로 더한 후 출력하여 해결하였다.


**테스트 마다 수행**
```java

int N = Integer.parseInt(sc.nextLine());
int[] distance = new int[N];
boolean[] isBound = new boolean[N];

for (int i = 0; i < N; i++) {
  distance[i] = Math.abs(sc.nextInt()) + Math.abs(sc.nextInt());
  sc.nextLine();
}

int step = 0;
while (true) {
  for (int i = 0; i < N; i++) {
    if (distance[i] - step > 1) {
      distance[i] -= step;
    } else {
      isBound[i] = true;
      distance[i] = Math.abs(distance[i]-step)%2;
    }
  }

  if (checkAllInBound(isBound)) {
    break;
  }
  step++;
}
int d = distance[0];
if (Arrays.stream(distance).allMatch(i -> i == d)) {
  if (d%2 == 1) {
    if (step%2 == 1) {
      step+=2;
    } else {
      step+=1;
    }
  }
  System.out.println("#"+test_case+" "+step);
} else {
  System.out.println("#"+test_case+" -1");
}

```

**모든점이 원점으로 부터 거리`1`에 들어왔는지 검사**

```java
private static boolean checkAllInBound(boolean[] isBound) {
  for (int i = 0; i < isBound.length; i++) {
    if (isBound[i] == false)
      return false;
  }
  return true;
}
```
