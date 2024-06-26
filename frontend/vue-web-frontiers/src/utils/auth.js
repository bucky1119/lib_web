import Cookies from "js-cookie";

const TokenKey = "vue_web_frontiers_token";

const UsernameKey = "vue_web_frontiers_username";

const RoleKey = "vue_web_frontiers_role";

const UserIdKey = "vue_web_frontiers_userId";

export function getToken() {
  return Cookies.get(TokenKey);
}

export function setToken(token) {
  return Cookies.set(TokenKey, token);
}

export function removeToken() {
  return Cookies.remove(TokenKey);
}

export function getUsername() {
  return Cookies.get(UsernameKey);
}

export function setUsername(username) {
  return Cookies.set(UsernameKey, username);
}

export function removeUsername() {
  return Cookies.remove(UsernameKey);
}

export function getRole() {
  return Cookies.get(RoleKey);
}

export function setRole(role) {
  return Cookies.set(RoleKey, role);
}

export function removeRole() {
  return Cookies.remove(RoleKey);
}

export function getUserId() {
  return Cookies.get(UserIdKey);
}

export function setUserId(userId) {
  return Cookies.set(UserIdKey, userId);
}

export function removeUserId() {
  return Cookies.remove(UserIdKey);
}
