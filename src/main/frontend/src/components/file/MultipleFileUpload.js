import React, { useState } from 'react'

const MultipleFileUpload = () => {

    const [files, setFiles] = useState([]);

    const handleFileChange = (event) => {
        const seletedFiles = Array.from(event.target.files);
        setFiles(seletedFiles);
    };

    const handleUpload = async () => {
        const formData = new FormData();

        files.forEach((file, index) => {
            formData.append(`file${index + 1}`, file);
        });

        try {
            const response = await fetch('http://localhost:8080/file/upload', {
                method: 'POST',
                body: formData,
            });

            console.log(response);
        } catch (error) {
            console.error(error);
        }
    };

  return (
    <div>
        <input type="file" multiple onChange={ handleFileChange } />
        <button onClick={ handleUpload }>Upload</button>
    </div>
  );
};

export default MultipleFileUpload;