import { Request,Response } from 'express';//Nhúng kiểu Request và Response từ module express
import { Op, Sequelize } from 'sequelize';

// Model
import ThongTinThanhVien from '../../model/ThongTinThanhVien';


export const listUser=async(req:Request,res:Response)=>{
    try {
        const users= await ThongTinThanhVien.findAll({  
            raw:true
        });
        res.status(200).json(users);
    } catch (error) {
        res.status(500).json({message:'Lỗi '+error.message});
    }
}