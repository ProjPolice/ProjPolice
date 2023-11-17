import { initializeApp } from 'firebase/app';
import { getMessaging, getToken } from 'firebase/messaging';

const ENV = import.meta.env;

const firebaseConfig = {
  apiKey: ENV.VITE_FIREBASE_API_KEY,
  authDomain: ENV.VITE_FIREBASE_AUTH_DOMAIN,
  projectId: ENV.VITE_FIREBASE_PROJECT_ID,
  storageBucket: ENV.VITE_FIREBASE_STORAGE_BUCKET,
  messagingSenderId: ENV.VITE_FIREBASE_MESSAGING_SENDER_ID,
  appId: ENV.VITE_FIREBASE_APP_ID,
  measurementId: ENV.VITE_FIREBASE_MEASUREMENT_ID,
};

const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

export const requestPermission = () => {
  Notification.requestPermission().then((permission) => {
    console.log(`Permission is ${permission}`);
    if (permission === 'granted') {
      return getToken(messaging, { vapidKey: ENV.VITE_FIREBASE_MESSAGING_KEY })
        .then((token) => {
          localStorage.setItem('FirebaseToken', token);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  });
};
