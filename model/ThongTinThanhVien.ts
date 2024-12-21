import { DataTypes } from 'sequelize';
import sequelize from '../config/database';

const ThongTinThanhVien= sequelize.define("ThongTinThanhVien",{
    maSinhVien:{
        type:DataTypes.STRING,
        allowNull:false,
        primaryKey:true
    },
    hoTen:{
        type:DataTypes.STRING,
        allowNull:false
    },
    lop:{
        type:DataTypes.STRING,
        allowNull:false
    }
},{
    tableName: "ThongTinThanhVien",
    timestamps: false
})

export default ThongTinThanhVien;