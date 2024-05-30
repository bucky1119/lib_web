import { getArticleList } from "@/api/dashboard";

const getState = () => {
  return {
    articleList: [],
  };
};

const state = getState();

const mutations = {
  SET_ARTICLELIST: (state, articleList) => {
    state.articleList = articleList;
  },
};

const actions = {
  // 获取文章列表
  getArticleList({ commit }, searchForm) {
    const { username, password } = searchForm;
    return new Promise((resolve, reject) => {
      getArticleList({ username: username.trim(), password: password })
        .then((response) => {
          const { data } = response;
          commit("SET_ARTICLELIST", data);
          setToken(data);
          resolve();
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
};
