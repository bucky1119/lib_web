import request from "@/utils/request";

export function getArticleList(data) {
  return request({
    url: "/api/homepage",
    method: "post",
    data,
  });
}

export function deleteArticle(params) {
  return request({
    url: `/api/articles/${params}`,
    method: "delete",
    // params,
  });
}

export function updateArticle(params, data) {
  return request({
    url: `/api/articles/${params}`,
    method: "put",
    data,
  });
}

export function rateArticle(params, data) {
  return request({
    url: `/api/ratings/${params}`,
    method: "post",
    data,
  });
}

export function getAverScore(params) {
  return request({
    url: `/api/ratings/average/${params}`,
    method: "get",
  });
}

export function getHistoryRate(params) {
  return request({
    url: `/api/ratings/history/${params}`,
    method: "get",
  });
}
