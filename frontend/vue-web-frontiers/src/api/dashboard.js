import request from "@/utils/request";

export function getArticleList(params) {
  return request({
    url: "/vue-admin-template/table/list",
    method: "get",
    params,
  });
}
