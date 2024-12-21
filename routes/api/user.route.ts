import express,{Router} from 'express';
const router : Router = express.Router();
import multer from 'multer';

import * as userController from '../../controller/api/user.controller';

//Middleware
import * as uploadCloud from '../../middleware/uploadCloudinary.middleware';
const upload = multer();

router.get('/list',userController.listUser);
export default router;