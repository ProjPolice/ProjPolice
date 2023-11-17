export const getAccessToken = () => {
  const sessionStorageItem = sessionStorage.getItem('sessionStorage');
  if (sessionStorageItem) {
    const accessToken = JSON.parse(sessionStorageItem).accessToken;
    return accessToken;
  }
  return undefined;
};
