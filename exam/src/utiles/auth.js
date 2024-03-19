import Cookies from 'js-cookie'


const adminInfo = "adminInfo"

//获取用户信息
export function getAdminInfo() {
  const admin = Cookies.get(adminInfo)
  if(admin){
    return JSON.parse(admin)
  }
  return ''
}
//存储用户信息
export function setAdminInfo(admin) {
  return Cookies.set(adminInfo, JSON.stringify(admin))
}
//移除用户信息
export function removeAdminInfo() {
  return Cookies.remove(adminInfo)
}
