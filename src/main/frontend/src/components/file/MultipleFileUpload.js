import React, { useState } from 'react'

const MultipleFileUpload = () => {

    const [files, setFiles] = useState([]);

    const handleFileChange = (e) => {
        setFiles(e.target.files);
    };

    const handleUpload = async () => {
        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            formData.append('files', files[i]);
        }

        try {
            const response = await fetch('http://localhost:8080/upload/files', {
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