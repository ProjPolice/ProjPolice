self.addEventListener('install', function () {
  console.log('Installing');
  self.skipWaiting();
});

self.addEventListener('activate', function () {
  console.log('Activate');
});

self.addEventListener('push', function (event) {
  if (!event.data.json()) {
    return;
  } else {
    const result = event.data.json().notification;
    const title = result.title;
    const options = {
      body: result.body,
      icon: result.image,
      tag: result.tag,
      ...result,
    };
    console.log(event);
    self.registration.showNotification(title, options);
  }
});

self.addEventListener('notificationclick', function (event) {
  const url = `https://projpolice.com/`;
  event.notification.close();
  // eslint-disable-next-line
  event.waitUntil(clients.openWindow(url));
});
