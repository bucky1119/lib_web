import { login, signin } from "@/api/user";
import {
  getToken,
  setToken,
  removeToken,
  setUsername,
  setRole,
  setUserId,
} from "@/utils/auth";

// import { resetRouter } from "@/router";

const getDefaultState = () => {
  return {
    token: getToken(),
    name: "",
    roles: "",
  };
};

const state = getDefaultState();

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState());
  },
  SET_TOKEN: (state, token) => {
    state.token = token;
  },
  SET_NAME: (state, name) => {
    state.name = name;
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles;
  },
};

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo;
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password })
        .then((response) => {
          const { data, token } = response;
          commit("SET_TOKEN", token);
          setToken(token);
          // resolve();
          // get user info
          const { role, username, id } = data;

          // // roles must be anon-empty array
          // if (!roles || roles.length <= 0) {
          //   reject("getInfo: roles must be a non-null array!");
          // }

          commit("SET_ROLES", role);
          commit("SET_NAME", username);
          setRole(role);
          setUsername(username);
          setUserId(id);
          resolve(data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // user  signing
  signin({ commit }, userInfo) {
    // console.log(userInfo, "userInfo");
    const { username, password, role_token } = userInfo;
    return new Promise((resolve, reject) => {
      signin(
        { token: role_token },
        {
          username: username.trim(),
          password: password,
        }
      )
        .then((response) => {
          const { data } = response;
          // commit("SET_TOKEN", data.token);
          // setToken(data.token);
          resolve();
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // get user info
  // getInfo({ commit, state }) {
  //   return new Promise((resolve, reject) => {
  //     getInfo(state.token)
  //       .then((response) => {
  //         const { data } = response;

  //         if (!data) {
  //           reject("Verification failed, please Login again.");
  //         }

  //         const { roles, name } = data;

  //         // roles must be a non-empty array
  //         if (!roles || roles.length <= 0) {
  //           reject("getInfo: roles must be a non-null array!");
  //         }

  //         commit("SET_ROLES", roles);
  //         commit("SET_NAME", name);
  //         // commit("SET_AVATAR", avatar);
  //         resolve(data);
  //       })
  //       .catch((error) => {
  //         reject(error);
  //       });
  //   });
  // },

  // // user logout
  // logout({ commit, state }) {
  //   return new Promise((resolve, reject) => {
  //     logout(state.token)
  //       .then(() => {
  //         removeToken(); // must remove  token  first
  //         resetRouter();
  //         commit("RESET_STATE");
  //         resolve();
  //       })
  //       .catch((error) => {
  //         reject(error);
  //       });
  //   });
  // },

  // remove token
  resetToken({ commit }) {
    return new Promise((resolve) => {
      removeToken(); // must remove  token  first
      commit("RESET_STATE");
      resolve();
    });
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
