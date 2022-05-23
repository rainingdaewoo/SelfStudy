import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Button } from 'react-bootstrap';

const Detail = (props) => {
    const propsParam = useParams();
    const id = propsParam.id;

    const [book, setBook] = useState({
        id: "",
        title: "",
        author: ""
    });

    useEffect( () => {
        fetch("http://localhost:8080/book/" + id).then( res => res.json() ).then( res => {
            setBook(res);
        });
    }, [])

    const deleteBook = () => {
        fetch('http://localhost:8080/book/' + id, {
            method: 'DELETE',
        })
            .then((res) => res.text())
            .then((res) => {
                console.log(res);
                if (res === 'OK') {
                    window.location.href = "/";
                } else {
                alert('삭제실패');
                }
            });
        };
    
    const updateBook = () => {
        window.location.href = "/updateForm/" + id; // 페이지 이동이 되어버림 나중에 변경 필요
        //props.history.push('/updateForm/' + id);
    }

    return (
        <div>
            <h1>상세보기</h1>
            <Button onClick={ updateBook }>수정</Button>
            {" "}
            <Button onClick={ deleteBook }>삭제</Button>
            <hr />
            <h3>{book.author}</h3>
            <h1>{book.title}</h1>

        </div>
    );
};

export default Detail;