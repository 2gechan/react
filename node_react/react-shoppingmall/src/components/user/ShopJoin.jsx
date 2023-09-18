import { useNavigate } from "react-router-dom";

const ShopJoin = (props) => {
  const { user, axios, joinInputChangeHandler } = props;
  const navigate = useNavigate();

  const joinButtonClickHandler = async (e) => {
    console.log(user);

    const response = await axios.post("/join", user);

    console.log(response.status);
    if (response.status === 200) {
      navigate("/");
    } else {
      alert("회원가입에 실패했습니다.");
    }
  };

  return (
    <div className="main joinForm">
      <div className="join input">
        <label htmlFor="">아이디</label>
        <input
          type="text"
          placeholder="ID"
          name="user_id"
          onChange={joinInputChangeHandler}
        />
      </div>
      <div className="join input">
        <label htmlFor="">패스워드</label>
        <input
          type="password"
          placeholder="PASSWORD"
          name="user_password"
          onChange={joinInputChangeHandler}
        />
      </div>
      <div className="join input">
        <label htmlFor="">이름</label>
        <input
          type="text"
          placeholder="NAME"
          name="user_name"
          onChange={joinInputChangeHandler}
        />
      </div>
      <div className="join input">
        <label htmlFor="">전화번호</label>
        <input
          type="text"
          placeholder="PHONE NUMBER"
          name="user_tel"
          onChange={joinInputChangeHandler}
        />
      </div>
      <div className="join button">
        <button type="button" onClick={joinButtonClickHandler}>
          회원가입
        </button>
      </div>
    </div>
  );
};

export default ShopJoin;
