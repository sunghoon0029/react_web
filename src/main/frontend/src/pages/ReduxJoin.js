import React, { useState } from 'react'
import { useDispatch } from 'react-redux'

const ReduxJoin = () => {

    const dispatch = useDispatch();

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const onChangeName = (e) => {
        setName(e.target.value);
    };
    const onChangeEmail = (e) => {
        setEmail(e.target.value);
    };
    const onChangePassword = (e) => {
        setPassword(e.target.value);
    };
    const onChangeConfirmPassword = (e) => {
        setConfirmPassword(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            return alert('비밀번호가 일치하지 않습니다.')
        }

        let body = {
            name,
            email,
            password,
        }
    };

  return (
    <div>ReduxJoin</div>
  );
};

export default ReduxJoin;