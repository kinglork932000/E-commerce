USE ECOMMERCE;
INSERT INTO CATEGORIES ("categoryid", "name", "biggroup")
VALUES ('BAS','Basic Mobile','Phone'),
('CAM','Camera','Other'),
('CON','Console','Other'),
('DES','Desktop','Computer'),
('MON','Monitor','Accessories'),
('HEA','Headphone','Accessories'),
('KEY','Keyboard','Accessories'),
('LAP','Laptop','Computer'),
('TAB','Tablet','Phone'),
('MIC','Microphone','Accessories'),
('MOU','Mouse','Accessories'),
('SMA','Smartphone','Phone'),
('SPE','Speaker','Accessories')

GO

INSERT into products (brand,categoryid,product_name,price,quantity,description)
--tablet
values('Samsung', 'TAB', 'Samsung Galaxy Tab A7(2020)', 340, 100, 'Your new window to the world - With a slim design, a vibrant entertainment system and outstanding performance, the new Galaxy Tab A7 is a stylish new companion for your life. Dive head-first into the things you love, and easily share your favourite moments.'),
('Lenovo','TAB','Lenovo Tab E10 TB-X104L', 129, 100, 'The Lenovo Tab E10 is the choice for parents seeking a vibrant multimedia tablet that the whole family can enjoy. Its large 10" HD screen and enhanced Dolby Atmos® audio is great for movies, pictures, and games. Child-friendly features like blocking inappropriate content put parents in control of what kids see and do, for added peace of mind.'),
('Samsung', 'TAB', 'Samsung Galaxy Tab A8 8" T295 (2019)', 160, 100, 'The tablet that goes where you go - Discover a practical companion in Galaxy Tab A (8.0'', 2019), a tablet that excels at the basics and adds a lot more. With a design that’s easy to carry with one hand, it is slim, compact, and portable, the ideal blend of performance, design and attainability.'),
('Apple', 'TAB', 'iPad Pro 11 inch Wifi 128GB (2020)', 910, 100, 'The world’s most advanced mobile display. - The edge-to-edge Liquid Retina display is not only gorgeous and immersive, but also features incredibly advanced technologies. From True Tone to ProMotion, everything looks stunning and feels responsive on iPad Pro.'),
('Apple', 'TAB', 'iPad Pro 12.9 inch Wifi Cellular 128GB (2020)', 1344, 100, 'The world’s most advanced mobile display. - The edge-to-edge Liquid Retina display is not only gorgeous and immersive, but also features incredibly advanced technologies. From True Tone to ProMotion, everything looks stunning and feels responsive on iPad Pro.'),
('Apple', 'TAB', 'iPad Air 4 Wifi 256GB (2020)', 888, 100, 'The Apple iPad Air (2020) 10.9 inches 256GB WiFi Silver is a slim and light iPad with a powerful A14 Bionic processor. This iPad Air has a larger 10.9-inch screen than its predecessor. The Lightning port has been replaced by USB-C port and the familiar home button has also disappeared. You can now unlock your device with TouchID via the on/off button on the top.'),

/*camera*/
('Canon', 'CAM', 'Canon EOS M50 Kit EF-M15-45mm F3.5-6.3 IS STM', 723, 100, 'Modern, hassle free shooting for superb results. Compact, sleek and intuitive shooting experience. Clever connectivity and control with Wi-Fi & Bluetooth. Inspiring technologies for stories you will be proud of. Digital Camera EOS M50 body, Camera Cover R-F-4, EF-M15-45 S f/3.5-6.3 IS STM, Lens Cap E-49, Lens Dust Cap EB, Strap EM-200DB, Battery Charger LC-E12E'),
('Canon', 'CAM', 'Canon EOS 800D Kit EF-S18-55mm F4-5.6 IS STM', 672,100, 'AF experience beyond expectation - Experience the fast focusing speed of EOS 800D, which also features an intuitive, easy-to-use UI that guides you to shoot aesthetically-appealing images. The DIGIC 7 image processor and 24.2-megapixel APS-C sensor combine to produce images more accurate and detailed than ever, great for preserving memories of holidays, special occasions and time spent with loved ones.'),
('Nikon', 'CAM', 'Nikon D3500 Kit AF-P DX Nikkor 18-55mm F3.5-5.6G VR', 415, 100, 'A DSLR that is as easy to use as a point and shoot camera. Compact, comfortable design that''s great for travel and special events. Image sensor that''s 15x larger than those used in typical smartphones for sharper, clearer pictures. Works with Nikon’s snap bridge app for sharing photos with a compatible smartphone or tablet. 1080p full hd videos with monaural sound at the touch of a button. Bluetooth version 4.1'),
/*console*/
('Sony', 'CON', 'PS4 1TB Slim Bundled', 27.99, 100, 'Play the greatest games and PS4 exclusive, take your adventures online with PS plus and Stream or download TV shows and movies from Netflix, PS store or wherever you get your favourite entertainment. Hdr-enabled PS4 games burst into life with incredible colour and clarity on an HDR TV, delivering a more vibrant, realistic spectrum of colours. Store your games, apps, screenshots and videos with 1TB model – slimmer and lighter than the original PS4 model and available in jet Black'),
('Microsoft', 'CON', 'Xbox Series S', 34.99, 100, 'Introducing the Xbox series S, the smallest, sleekest Xbox console ever. Experience the speed and performance of a next-gen all-digital console at an accessible price point. Go all-digital and enjoy disc-free, next-gen gaming with the smallest Xbox console ever made. Experience next-gen speed and performance with the Xbox velocity architecture, powered by a custom SSD and integrated software. Play thousands of digital games from four generations of Xbox with backward compatibility, including optimized titles at launch.'),
('Crispy Deals', 'CON', 'Crispy Deals 2 Player Tv Game Set', 16, 100, 'Games are inbuilt, or a Free Cassete is available in package, if not there means, the free games are inbuilt in console. Images are Real Photo Shoot of Products, and Designs Are Newly Applied in a Subsequent time. Two players Playable Tv video game set. Suitable for Kids, and School students. Enjoy the Stage Level, Sports,Shooting, Arcaud, and Racing Games. For these Games you need to buy 8 bit game cartridges individually. and The Package contains only O)ne Default Game Cartridge. '),
/*desktop*/
('Lenovo', 'DES', 'Lenovo Ideacentre 510S Desktop, Warm Silver', 336, 100, 'Processor: 9th Generation Inter Core i3-9100 Processor, 3.6 GHz base speed, up to 4.2 GHz maximum speed, 4 Cores, 6MB Cache. Memory and Storage: 4GB DDR4 RAM, expandable to 16GB with integrated UHD graphics | Storage: 1TB HDD. Operating System: Pre-Loaded Windows 10 Home with Lifetime Validity | In The Box: Desktop, Keyboard, Mouse, User Manual. Ports: 4 USB 3.1, 4 USB 2.0, Headphone/Mic combo jack (3.5mm), Microphone (3.5mm), Ethernet (RJ-45), DP, HDMI, VGA, Serial port, Audio port | Without CD Drive. Warranty: 1 Year On-site Warranty'),
('REO', 'DES', 'REO Desktop Intel Core i5 650 3.2 Ghz/4 GB DDR3 Ram', 261, 100, 'Slim, Elegant, Best in class high speed Desktop for Home, Office use. Intel Core i5 650 3.2Ghz, Reo Intel H55 Motherboard, Reo 4 GB DDR3 RAM. Superfast 120GB SSD Hard Disk with additional 500GB SATA (7200 RPM) Hard Disk (Total 2 Hard Disk inside the System). Intel HD Graphics, VGA, HDMI Supports, 500 Watt SMPS, Wi-Fi Ready. Integrated extra cooling fan for better heat management, 6 USB ports, front USB, front Audio')

INSERT INTO PRODUCTS (CATEGORYID,PRODUCT_NAME,BRAND,DESCRIPTION,QUANTITY,PRICE)
VALUES
--monitor (sua nhe)
('MON','LG 27GL83A-B','LG','27 Inch Ultragear QHD IPS 1ms NVIDIA G-SYNC Compatible Gaming Monitor, Black',20,379.99),
('MON','Acer SB220Q bi','ACER','21.5 Inches Full HD (1920 x 1080) IPS Ultra-Thin Zero Frame Monitor (HDMI & VGA Port), Black',20,93.99),
('MON','Sceptre 35 Inch Curved UltraWide 21','Sceptre','9 LED Creative Monitor QHD 3440x1440 Frameless AMD Freesync HDMI DisplayPort Up to 100Hz, Machine Black 2020',20,379.97),
--keyboard
('KEY','Azio Retro Classic Bluetooth','Azio','Luxury Vintage Backlit Mechanical Keyboard, MK-RETRO-L-03B-US',20,219.99),
('KEY','Kinesis Advantage2 Ergonomic','KINESIS','Ergonomic',20,323.90),
('KEY','Majestouch Convertible 2 TKL','FILCO','Ergonomic',20,179.00),
--laptop
('LAP','Apple MacBook Pro','Apple','16-inch, 16GB RAM, 512GB Storage',20,2249.00),	
('LAP','ASUS F512DA-EB51 VivoBook','ASUS','15 Thin And Light Laptop, 15.6” Full HD, AMD Quad Core R5-3500U CPU, 8GB DDR4 RAM, 256GB PCIe SSD, AMD Radeon Vega 8 Graphics, Windows 10 Home,Slate Gray',20,589.00),
('LAP','Acer Predator Helios 300 PH315-53-71VG','Acer','Intel i7-10750H, NVIDIA GeForce RTX 2070 Max-Q 8GB, 15.6" FHD 240Hz 3ms IPS, 16GB Dual-Channel DDR4, 512GB NVMe SSD, 1TB HDD, WiFi 6, RGB KB',20,1999.00),
('LAP','Acer Predator Triton 500 PT515-52-73L3','Acer','Intel i7-10750H, NVIDIA GeForce RTX 2070 SUPER, 15.6" FHD NVIDIA G-SYNC Display, 300Hz, 16GB Dual-Channel DDR4, 512GB NVMe SSD, RGB Backlit KB',20,1659.99),
('LAP','Acer Predator Triton 500 Thin & Light','Acer',' Intel Core i7-9750H, GeForce RTX 2060 with 6GB, 15.6" Full HD 144Hz 3ms IPS Display, 16GB DDR4, 512GB PCIe NVMe SSD, RGB Keyboard',20,1628.99),
('LAP','ZenBook 15','ASUS','15.6” UHD 4K NanoEdge Display, Intel Core i7-10510U, GeForce GTX 1650, 16GB, 512GB PCIe SSD, ScreenPad 2.0, Amazon Alexa Compatible, Windows 10, Icicle Silver',20,1148.59),
('LAP','Apple MacBook Pro','Apple','16-inch, 16GB RAM, 512GB Storage',20,2249.00),
--smartphone
('SMA','iPhone 12 Pro Max 512GB','Apple','6.7-inch Super Retina XDR display, Pro 12MP camera system: Ultra Wide, Wide, and Telephoto cameras',20,1099),
('SMA','iPhone 12 256GB','Apple','5G speed. A14 Bionic, the fastest chip in a smartphone. An edge-to-edge OLED display. Ceramic Shield with four times better drop performance. And Night mode on every camera. iPhone 12 has it all — in two perfect sizes.',20,799),
('SMA','iPhone SE','Apple','iPhone SE packs our powerful A13 Bionic chip into our most popular size at our most affordable price. It’s just what you’ve been waiting for.',20,399),
('SMA','iPhone 12 128GB','Apple','6.1-inch (diagonal) all-screen LCD Multi-Touch display with IPS technology',20,599),
('SMA','Galaxy S21 Ultra 5G','Samsung','The real metal finish on the main camera adds unity to the design and accentuates the luxury of the haze texture. It’s a bold new camera design in a category of its own.',20,1199),
('SMA','Galaxy Z Fold2 5G','Samsung','A mind-bending glass screen that folds like a book. A hands-free camera that shoots when you wave. A precision crafted hinge that transforms a cutting-edge smartphone into something much more.',20,999),
('SMA','Galaxy A71 5G','Samsung','A smartphone that delivers all of the incredible features and performance you want from a Galaxy device, at an amazing value. With blazing 5G speed, a long-lasting battery, and an impressive quad camera, it’s time to see why the A71 5G gives you so much more than you bargained for.',20,449),
('SMA','Vsmart Joy 4','Vsmart','Surpassing popular screen standards, Joy 4 offers an impeccable visual experience thanks to the 6.53 inch borderless screen and trendy hole-punch camera design. Leading the segment with high-end FHD+ 1080x2340 resolution, Joy 4 makes every frame sharp and vivid beyond expectation.',20,120),
('SMA','Vsmart ARIS','Vsmart','The birth of Vsmart Aris Series marks the pioneering position of Vietnamese technology in the smartphone arena. Successfully launching Camera Under Display and Quantum Physics Security, Vsmart is taking the leap to reinvent smartphone experiences, and provides users front row seats to try world’s breakthrough technologies.',20,280),
('SMA','Hoawei Mate 40 Pro','Huawei','See beyond the horizon, explore the unknown, and leap boldly into the future, with unprecedented power, speed and imagination. Embrace the intelligence and live an intimate life with innovative technology on the HUAWEI Mate 40 Pro.',20,1429),
--mouse
('MOU','Razer DeathAdder V2 Pro','Razer','With over 10 million Razer DeathAdders sold, the most successful gaming mouse of all time sheds its cord for comfort without limits. All hail the Razer DeathAdder V2 Pro—a wireless ergonomic gaming mouse that ushers in a new reign of dominance through total freedom of movement and control.',20,129),
('MOU','Razer Naga X','Razer','Whether you’re a tank, healer or DPS, always be ready to raid with the Razer Naga X—an ergonomic MMO gaming mouse with 16 programmable buttons. Made lighter and armed with other best-in-slot features, it’s time you got geared up to top the logs.',20,79),
('MOU','Logitech Pebble M350','Logitech','Make any space minimalist, modern, and silent with Logitech Pebble – the portable mouse that fits your curated lifestyle and goes wherever life takes you.',20,29),
('MOU','Logitech MX Vertical','Logitech','MX Vertical is an advanced ergonomic mouse that combines science-driven design with the elevated performance of Logitech’s MX series.',20,99),
('MOU','Logitech ERGO M575','Logitech','Minimize movement and maximize comfort with ERGO M575 – a wireless trackball with a sculpted ergonomic shape. With no need to move your arm around to move the cursor, your hand and arm stay relaxed.',20,49),
--speaker
('SPE','TAMPROAD Portable Bluetooth Speakers','TAMPROAD','Bluetooth Speaker Support Bluetooth connection, Support FM radio, TF Card music playback, AUX input, U-disk music playback, intelligent remote contrl and digital display. Powerful features, multiple choices to enrich your life.',20,45),
('SPE','The Bose SoundLink Revolve','Bose','Deep, loud and immersive sound, with true 360 degree coverage, Built-in mic for speakerphone to take clear conference or personal calls out loud with a wireless range of approximately 30 feet.',20,199),
--headphone
('HEA','Apple AirPods Pro','Apple','InEar, Active noise cancellation for immersive sound, Transparency mode for hearing and connecting with the world around you',20,150),
('HEA','Sony Noise Cancelling Headphones WHCH710N','Sony','Noise cancellation automatically senses your environment with Dual Noise Sensor Technology, Long-lasting listening with up to 35 hours of battery and quick charging',20,98),
('HEA','Sony ZX Series Wired On-Ear Headphones','Sony','Connectivity Technology: Wired, 30mm drivers for rich, full frequency response',20,20),
--microphone
('MIC','USB Condenser Recording Microphone for Computer','DSCHLZY','Upgraded Functions Ever Seen. Professional Sound Quality. Plug and Play. Simple and easy to use.',20, 33),
('MIC','Blue Snowball iCE USB Mic','Blue','Custom condenser capsule offers crystal clear audio for Skype, Messages and FaceTime. Add crystal clear audio to recordings for YouTube. Frequency Response: 40 –18 kHz.',20, 85),
('MIC','Blue Yeti Nano Professional','Blue','Perfect for podcasting, game streaming, Skype calls, YouTube or music. No-latency headphone output, headphone volume and mic mute.',20, 100),
--basicphone
('BAS','Nokia 215 4G','Nokia','SUPERFAST 4G. DURABILITY & LONG-LASTING BATTERY LIFE. ENTERTAINMENT. ERGONOMIC DESIGN',20,40),
('BAS','Nokia 5310','Nokia','The return of a classic. Music in its blood. Stay connected 24/7',20,46.5)