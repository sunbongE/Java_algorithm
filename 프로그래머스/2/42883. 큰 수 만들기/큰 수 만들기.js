function solution(number, k) {
    const stack = []; // 큰 숫자를 저장할 스택
    let count = k; // 제거해야 할 숫자의 개수

    // 숫자를 왼쪽에서부터 하나씩 확인
    for (let num of number) {
        // 스택의 마지막 숫자가 현재 숫자보다 작으면 제거
        // 제거할 수 있는 숫자(k)가 남아있는 경우만 제거 가능
        while (count > 0 && stack.length > 0 && stack[stack.length - 1] < num) {
            stack.pop(); // 작은 숫자를 제거
            count--; // 제거할 수 있는 숫자 감소
        }
        stack.push(num); // 현재 숫자를 스택에 추가
    }

    // 만약 제거해야 할 숫자가 남아있다면, 뒤에서부터 제거
    // 이 경우는 스택에 있는 숫자들이 내림차순일 때 발생
    stack.splice(stack.length - count, count);
    
    // 스택에 남은 숫자들을 이어붙여서 최종 문자열로 반환
    return stack.join('');
}
