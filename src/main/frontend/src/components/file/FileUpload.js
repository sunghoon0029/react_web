import React, { useState } from 'react'

const FileUpload = () => {

    const [file, setFile] = useState(null);

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const handleUpload = async () => {
        const formData = new FormData();
        formData.append('file', file);

        try {
            const response = await fetch('http://localhost:8080/upload/file', {
                method: 'POST',
                body: formData,
            });

            console.log(response);
        } catch (error) {
            console.error(error);
        }
    };;

  return (
    <div>
        <input type="file" onChange={ handleFileChange } />
        <button onClick={ handleUpload }>Upload</button>
    </div>
  );
};

export default FileUpload;