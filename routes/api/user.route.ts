import express,{Router} from 'express';
const router : Router = express.Router();
import multer from 'multer';

import * as userController from '../../controller/api/user.controller';

//Middleware
const upload = multer();

router.get('/list',userController.listUser);
router.post('/add',userController.addUser);
router.get('/detail',userController.detailUser);
router.post('/edit',userController.editUser);
router.post('/delete',userController.deleteUser);
export default router;