import {MigrationInterface, QueryRunner} from "typeorm";

export class first1565923904611 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<any> {
        await queryRunner.query(`CREATE TABLE "employees" ("id" int NOT NULL IDENTITY(1,1), "name" nvarchar(255) NOT NULL, "email" nvarchar(255) NOT NULL, "created_at" datetime2 NOT NULL CONSTRAINT "DF_e1f508d74b2f061e0248c2769cf" DEFAULT getdate(), "updated_at" datetime2 NOT NULL CONSTRAINT "DF_37738e835c865a9caf4635757fe" DEFAULT getdate(), CONSTRAINT "PK_b9535a98350d5b26e7eb0c26af4" PRIMARY KEY ("id"))`);
        await queryRunner.query(`CREATE TABLE "appointments" ("id" int NOT NULL IDENTITY(1,1), "timeslot" datetime NOT NULL, "created_at" datetime2 NOT NULL CONSTRAINT "DF_387464fb81909344c275230cc08" DEFAULT getdate(), "updated_at" datetime2 NOT NULL CONSTRAINT "DF_c5aff8c60e664ef9724f1cae097" DEFAULT getdate(), "employee_id" int, CONSTRAINT "PK_4a437a9a27e948726b8bb3e36ad" PRIMARY KEY ("id"))`);
        await queryRunner.query(`ALTER TABLE "appointments" ADD CONSTRAINT "FK_f4e3a19c74dac65a223368fa9a0" FOREIGN KEY ("employee_id") REFERENCES "employees"("id") ON DELETE NO ACTION ON UPDATE NO ACTION`);
    }

    public async down(queryRunner: QueryRunner): Promise<any> {
        await queryRunner.query(`ALTER TABLE "appointments" DROP CONSTRAINT "FK_f4e3a19c74dac65a223368fa9a0"`);
        await queryRunner.query(`DROP TABLE "appointments"`);
        await queryRunner.query(`DROP TABLE "employees"`);
    }

}
