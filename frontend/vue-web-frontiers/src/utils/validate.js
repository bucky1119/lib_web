/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path);
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validRoleToken(str) {
  const valid_map = ["adminSecret", "librarianSecret", "expertSecret"];
  return valid_map.indexOf(str.trim()) >= 0;
  librarianSecret;
}
