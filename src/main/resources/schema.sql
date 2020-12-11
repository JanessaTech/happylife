drop table if exists public.addr CASCADE;
drop table if exists public.item CASCADE;
drop table if exists public.order CASCADE;
drop table if exists public.saler CASCADE;
drop table if exists public.shop CASCADE;
drop table if exists public.shopping_cart CASCADE;
drop table if exists public."user" CASCADE;

CREATE TABLE "addr" (
"addr_id" uuid NOT NULL,
"user_id" uuid NOT NULL,
"addr_info" varchar(200) COLLATE "default" NOT NULL,
"receiver" varchar(20) COLLATE "default" NOT NULL,
"phone" int8 NOT NULL,
"create_date" timestamptz(0),
"update_date" timestamptz(0),
CONSTRAINT "addr_pkey" PRIMARY KEY ("addr_id")
)
WITHOUT OIDS;
ALTER TABLE "addr" OWNER TO "juan";

CREATE TABLE "item" (
"item_id" uuid NOT NULL,
"title" varchar(50) COLLATE "default" NOT NULL,
"shop_id" uuid NOT NULL,
"price" money NOT NULL,
"details" text COLLATE "default",
"create_date" timestamptz(0),
"update_date" timestamptz(0),
CONSTRAINT "item_pkey" PRIMARY KEY ("item_id")
)
WITHOUT OIDS;
ALTER TABLE "item" OWNER TO "juan";

CREATE TABLE "order" (
"order_id" uuid NOT NULL,
"user_id" uuid NOT NULL,
"shop_id" uuid NOT NULL,
"item_ids" uuid[] NOT NULL,
"total_price" money NOT NULL,
"status" varchar(20) COLLATE "default" NOT NULL,
"logistics_id" uuid,
"create_date" timestamptz(0) NOT NULL,
"update_date" timestamptz(0),
CONSTRAINT "order_pkey" PRIMARY KEY ("order_id")
)
WITHOUT OIDS;
ALTER TABLE "order" OWNER TO "juan";

CREATE TABLE "saler" (
"saler_id" uuid NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"password" varchar(20) COLLATE "default" NOT NULL,
"email" varchar(50) COLLATE "default",
"phone" int8,
"create_date" timestamptz(0),
"update_date" timestamptz(0),
CONSTRAINT "saler_pkey" PRIMARY KEY ("saler_id")
)
WITHOUT OIDS;
ALTER TABLE "saler" OWNER TO "juan";

CREATE TABLE "shop" (
"shop_id" uuid NOT NULL,
"shop_name" varchar(50) COLLATE "default" NOT NULL,
"saler_id" uuid NOT NULL,
"status" bool NOT NULL,
"create_date" timestamptz(0),
"update_date" timestamptz(0),
CONSTRAINT "shop_pkey" PRIMARY KEY ("shop_id")
)
WITHOUT OIDS;
ALTER TABLE "shop" OWNER TO "juan";

CREATE TABLE "shopping_cart" (
"shopping_id" uuid NOT NULL,
"item_ids" uuid[],
"user_id" uuid NOT NULL,
CONSTRAINT "shopping_cart_pkey" PRIMARY KEY ("shopping_id")
)
WITHOUT OIDS;
ALTER TABLE "shopping_cart" OWNER TO "juan";

CREATE TABLE "user" (
"user_id" uuid NOT NULL,
"name" varchar(20) COLLATE "default" NOT NULL,
"password" varchar(20) COLLATE "default" NOT NULL,
"sex" varchar(10) COLLATE "default",
"addr" varchar(50) COLLATE "default",
"profile" text COLLATE "default",
"create_date" timestamptz(0),
"update_date" timestamptz(0),
CONSTRAINT "user_pkey" PRIMARY KEY ("user_id")
)
WITHOUT OIDS;
ALTER TABLE "user" OWNER TO "juan";


ALTER TABLE "addr" ADD CONSTRAINT "fk_addr_user" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "item" ADD CONSTRAINT "fk_item_shop" FOREIGN KEY ("shop_id") REFERENCES "shop" ("shop_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "order" ADD CONSTRAINT "fk_order_shop" FOREIGN KEY ("shop_id") REFERENCES "shop" ("shop_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "order" ADD CONSTRAINT "fk_order_user" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "shop" ADD CONSTRAINT "fk_shop_owner" FOREIGN KEY ("saler_id") REFERENCES "saler" ("saler_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "shopping_cart" ADD CONSTRAINT "fk_cart_user" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;