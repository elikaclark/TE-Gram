// const CLOUDINARY_ULR = process.env.CLOUDINARY_URL;
// const CLOUDINARY_UPLOAD_PRESET = process.env.CLOUDINARY_PRESET;

const CLOUDINARY_ULR = 'https://api.cloudinary.com/v1_1/dtotytpog/image/upload';
const CLOUDINARY_UPLOAD_PRESET = 'lvizruov';

export default {

    newImage(formData){
        formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET);

        return fetch(CLOUDINARY_ULR, {
          method: 'POST',
          header: {
              'content-type': 'application/x-www-form-urlencoded'
          },
          body: formData
      });
    },
}
