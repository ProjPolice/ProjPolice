import { ChangeEvent, Dispatch, SetStateAction, useState } from 'react';

export const useImageInput = (
  initialData?: FormData,
): [FormData | undefined, (event: ChangeEvent<HTMLInputElement>) => void, string, Dispatch<SetStateAction<string>>] => {
  const [image, setImage] = useState(initialData);
  const [src, setSrc] = useState('');

  const handleImage = (event: ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    if (files) {
      const formData = new FormData();
      const reader = new FileReader();
      reader.onload = () => {
        const result = reader.result as string;
        setSrc(result);
      };
      formData.append('file', files[0]);
      reader.readAsDataURL(files[0]);
      setImage(formData);
    }
  };

  return [image, handleImage, src, setSrc];
};
