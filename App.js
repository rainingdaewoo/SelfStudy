import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

// 1. return 시에 하나의 Dom만 리턴할 수 있다.
// 2. 변수 선언은 let OR const로만 가능함.
// 3. if문 사용 불가. but, 삼항연산자는 가능
// 4. 조건부 렌더링
// 5. css 디자인
//    - 내부에 적는 방법 / 외부 파일에 적는 방법 / 라이브러리 사용(부트스트랩, compent-style)
let a = 10;
const b = 20;

const myStyle = {
  color: "red"
};

function App() {


  // let number = 1; // 상태값 아님
  const [number, setNumber] = useState(1);
  
  const add = () => {
    number++;
    console.log("add", number);
  }

  // 랜더링 시점 = 상태값 변경
  return (
    <div>
      <h1> 숫자: {number} </h1> 
      <button onClick={add}>더하기</button>    
    </div>
  );
  
}

export default App;
