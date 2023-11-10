import { ChangeEvent, Dispatch, SetStateAction, useState } from 'react';

export const useTextInput = (
  initialValue?: string,
): [string, (event: ChangeEvent<HTMLInputElement>) => void, Dispatch<SetStateAction<string>>] => {
  const [value, setValue] = useState(initialValue ? initialValue : '');

  const handleValue = (event: ChangeEvent<HTMLInputElement>) => {
    setValue(event.target.value);
  };

  return [value, handleValue, setValue];
};
