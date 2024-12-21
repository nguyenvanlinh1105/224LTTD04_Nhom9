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
export const detailUser=async(req:Request,res:Response)=>{
    const maSinhVien=req.query.maSinhVien;
    try {
        const user= await ThongTinThanhVien.findOne({
            where:{
                maSinhVien:maSinhVien
            },
            raw:true
        })
        res.status(200).json(user);
    } catch (error) {
        res.status(500).json({message:'Lỗi '+error.message});
    }
}

export const addUser = async(req:Request,res:Response)=>{
    const data=req.body;
    console.log(data);
    try {
        const user = await ThongTinThanhVien.create(req.body);
        res.status(201).json({message:"Tạo mới thành công"});
    } catch (error) {
        res.status(400).json({message:'Lỗi '+error.message});
    }
}

export const editUser=async(req:Request,res:Response)=>{
    const data=req.body;
    try {
        const user= await ThongTinThanhVien.update(data,{
            where:{
                maSinhVien:data.maSinhVien
            }
        });
        if(user[0]==1){
            res.status(200).json({message:"Cập nhật thành công"});
        }else{
            res.status(404).json({message:"Không tìm thấy thành viên"});
        }
    } catch (error) {
        res.status(500).json({message:'Lỗi '+error.message});
    }
}

export const deleteUser=async(req:Request,res:Response)=>{
    const maSinhVien=req.query.maSinhVien;
    try {
        const user= await ThongTinThanhVien.destroy({
            where:{
                maSinhVien:maSinhVien
            }
        });
        if(user==1){
            res.status(200).json({message:"Xóa thành công"});
        }else{
            res.status(404).json({message:"Không tìm thấy thành viên"});
        }
    } catch (error) {
        res.status(500).json({message:'Lỗi '+error.message});
    }
}