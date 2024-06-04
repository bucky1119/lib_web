import request from "@/utils/request";

export function getArticleList(params) {
  return request({
    url: "/api/homepage",
    method: "get",
    params,
  });
}
