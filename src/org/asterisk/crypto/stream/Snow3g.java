/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this licens
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.asterisk.crypto.stream;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import org.asterisk.crypto.helper.AbstractStreamEncrypter;
import org.asterisk.crypto.helper.Tools;
import org.asterisk.crypto.interfaces.Cipher;
import org.asterisk.crypto.interfaces.StreamCipher;

/**
 *
 * @author Sayantan Chakraborty
 */
public enum Snow3g implements StreamCipher {

    SNOW_3G;

    private static final ValueLayout.OfInt LAYOUT = Tools.BIG_ENDIAN_32_BIT;

    private static final int[] S1_T0 = {
        0xa56363c6, 0x847c7cf8, 0x997777ee, 0x8d7b7bf6, 0x0df2f2ff, 0xbd6b6bd6, 0xb16f6fde, 0x54c5c591,
        0x50303060, 0x03010102, 0xa96767ce, 0x7d2b2b56, 0x19fefee7, 0x62d7d7b5, 0xe6abab4d, 0x9a7676ec,
        0x45caca8f, 0x9d82821f, 0x40c9c989, 0x877d7dfa, 0x15fafaef, 0xeb5959b2, 0xc947478e, 0x0bf0f0fb,
        0xecadad41, 0x67d4d4b3, 0xfda2a25f, 0xeaafaf45, 0xbf9c9c23, 0xf7a4a453, 0x967272e4, 0x5bc0c09b,
        0xc2b7b775, 0x1cfdfde1, 0xae93933d, 0x6a26264c, 0x5a36366c, 0x413f3f7e, 0x02f7f7f5, 0x4fcccc83,
        0x5c343468, 0xf4a5a551, 0x34e5e5d1, 0x08f1f1f9, 0x937171e2, 0x73d8d8ab, 0x53313162, 0x3f15152a,
        0x0c040408, 0x52c7c795, 0x65232346, 0x5ec3c39d, 0x28181830, 0xa1969637, 0x0f05050a, 0xb59a9a2f,
        0x0907070e, 0x36121224, 0x9b80801b, 0x3de2e2df, 0x26ebebcd, 0x6927274e, 0xcdb2b27f, 0x9f7575ea,
        0x1b090912, 0x9e83831d, 0x742c2c58, 0x2e1a1a34, 0x2d1b1b36, 0xb26e6edc, 0xee5a5ab4, 0xfba0a05b,
        0xf65252a4, 0x4d3b3b76, 0x61d6d6b7, 0xceb3b37d, 0x7b292952, 0x3ee3e3dd, 0x712f2f5e, 0x97848413,
        0xf55353a6, 0x68d1d1b9, 0x00000000, 0x2cededc1, 0x60202040, 0x1ffcfce3, 0xc8b1b179, 0xed5b5bb6,
        0xbe6a6ad4, 0x46cbcb8d, 0xd9bebe67, 0x4b393972, 0xde4a4a94, 0xd44c4c98, 0xe85858b0, 0x4acfcf85,
        0x6bd0d0bb, 0x2aefefc5, 0xe5aaaa4f, 0x16fbfbed, 0xc5434386, 0xd74d4d9a, 0x55333366, 0x94858511,
        0xcf45458a, 0x10f9f9e9, 0x06020204, 0x817f7ffe, 0xf05050a0, 0x443c3c78, 0xba9f9f25, 0xe3a8a84b,
        0xf35151a2, 0xfea3a35d, 0xc0404080, 0x8a8f8f05, 0xad92923f, 0xbc9d9d21, 0x48383870, 0x04f5f5f1,
        0xdfbcbc63, 0xc1b6b677, 0x75dadaaf, 0x63212142, 0x30101020, 0x1affffe5, 0x0ef3f3fd, 0x6dd2d2bf,
        0x4ccdcd81, 0x140c0c18, 0x35131326, 0x2fececc3, 0xe15f5fbe, 0xa2979735, 0xcc444488, 0x3917172e,
        0x57c4c493, 0xf2a7a755, 0x827e7efc, 0x473d3d7a, 0xac6464c8, 0xe75d5dba, 0x2b191932, 0x957373e6,
        0xa06060c0, 0x98818119, 0xd14f4f9e, 0x7fdcdca3, 0x66222244, 0x7e2a2a54, 0xab90903b, 0x8388880b,
        0xca46468c, 0x29eeeec7, 0xd3b8b86b, 0x3c141428, 0x79dedea7, 0xe25e5ebc, 0x1d0b0b16, 0x76dbdbad,
        0x3be0e0db, 0x56323264, 0x4e3a3a74, 0x1e0a0a14, 0xdb494992, 0x0a06060c, 0x6c242448, 0xe45c5cb8,
        0x5dc2c29f, 0x6ed3d3bd, 0xefacac43, 0xa66262c4, 0xa8919139, 0xa4959531, 0x37e4e4d3, 0x8b7979f2,
        0x32e7e7d5, 0x43c8c88b, 0x5937376e, 0xb76d6dda, 0x8c8d8d01, 0x64d5d5b1, 0xd24e4e9c, 0xe0a9a949,
        0xb46c6cd8, 0xfa5656ac, 0x07f4f4f3, 0x25eaeacf, 0xaf6565ca, 0x8e7a7af4, 0xe9aeae47, 0x18080810,
        0xd5baba6f, 0x887878f0, 0x6f25254a, 0x722e2e5c, 0x241c1c38, 0xf1a6a657, 0xc7b4b473, 0x51c6c697,
        0x23e8e8cb, 0x7cdddda1, 0x9c7474e8, 0x211f1f3e, 0xdd4b4b96, 0xdcbdbd61, 0x868b8b0d, 0x858a8a0f,
        0x907070e0, 0x423e3e7c, 0xc4b5b571, 0xaa6666cc, 0xd8484890, 0x05030306, 0x01f6f6f7, 0x120e0e1c,
        0xa36161c2, 0x5f35356a, 0xf95757ae, 0xd0b9b969, 0x91868617, 0x58c1c199, 0x271d1d3a, 0xb99e9e27,
        0x38e1e1d9, 0x13f8f8eb, 0xb398982b, 0x33111122, 0xbb6969d2, 0x70d9d9a9, 0x898e8e07, 0xa7949433,
        0xb69b9b2d, 0x221e1e3c, 0x92878715, 0x20e9e9c9, 0x49cece87, 0xff5555aa, 0x78282850, 0x7adfdfa5,
        0x8f8c8c03, 0xf8a1a159, 0x80898909, 0x170d0d1a, 0xdabfbf65, 0x31e6e6d7, 0xc6424284, 0xb86868d0,
        0xc3414182, 0xb0999929, 0x772d2d5a, 0x110f0f1e, 0xcbb0b07b, 0xfc5454a8, 0xd6bbbb6d, 0x3a16162c
    };

    private static final int[] S1_T1 = {
        0x6363c6a5, 0x7c7cf884, 0x7777ee99, 0x7b7bf68d, 0xf2f2ff0d, 0x6b6bd6bd, 0x6f6fdeb1, 0xc5c59154,
        0x30306050, 0x01010203, 0x6767cea9, 0x2b2b567d, 0xfefee719, 0xd7d7b562, 0xabab4de6, 0x7676ec9a,
        0xcaca8f45, 0x82821f9d, 0xc9c98940, 0x7d7dfa87, 0xfafaef15, 0x5959b2eb, 0x47478ec9, 0xf0f0fb0b,
        0xadad41ec, 0xd4d4b367, 0xa2a25ffd, 0xafaf45ea, 0x9c9c23bf, 0xa4a453f7, 0x7272e496, 0xc0c09b5b,
        0xb7b775c2, 0xfdfde11c, 0x93933dae, 0x26264c6a, 0x36366c5a, 0x3f3f7e41, 0xf7f7f502, 0xcccc834f,
        0x3434685c, 0xa5a551f4, 0xe5e5d134, 0xf1f1f908, 0x7171e293, 0xd8d8ab73, 0x31316253, 0x15152a3f,
        0x0404080c, 0xc7c79552, 0x23234665, 0xc3c39d5e, 0x18183028, 0x969637a1, 0x05050a0f, 0x9a9a2fb5,
        0x07070e09, 0x12122436, 0x80801b9b, 0xe2e2df3d, 0xebebcd26, 0x27274e69, 0xb2b27fcd, 0x7575ea9f,
        0x0909121b, 0x83831d9e, 0x2c2c5874, 0x1a1a342e, 0x1b1b362d, 0x6e6edcb2, 0x5a5ab4ee, 0xa0a05bfb,
        0x5252a4f6, 0x3b3b764d, 0xd6d6b761, 0xb3b37dce, 0x2929527b, 0xe3e3dd3e, 0x2f2f5e71, 0x84841397,
        0x5353a6f5, 0xd1d1b968, 0x00000000, 0xededc12c, 0x20204060, 0xfcfce31f, 0xb1b179c8, 0x5b5bb6ed,
        0x6a6ad4be, 0xcbcb8d46, 0xbebe67d9, 0x3939724b, 0x4a4a94de, 0x4c4c98d4, 0x5858b0e8, 0xcfcf854a,
        0xd0d0bb6b, 0xefefc52a, 0xaaaa4fe5, 0xfbfbed16, 0x434386c5, 0x4d4d9ad7, 0x33336655, 0x85851194,
        0x45458acf, 0xf9f9e910, 0x02020406, 0x7f7ffe81, 0x5050a0f0, 0x3c3c7844, 0x9f9f25ba, 0xa8a84be3,
        0x5151a2f3, 0xa3a35dfe, 0x404080c0, 0x8f8f058a, 0x92923fad, 0x9d9d21bc, 0x38387048, 0xf5f5f104,
        0xbcbc63df, 0xb6b677c1, 0xdadaaf75, 0x21214263, 0x10102030, 0xffffe51a, 0xf3f3fd0e, 0xd2d2bf6d,
        0xcdcd814c, 0x0c0c1814, 0x13132635, 0xececc32f, 0x5f5fbee1, 0x979735a2, 0x444488cc, 0x17172e39,
        0xc4c49357, 0xa7a755f2, 0x7e7efc82, 0x3d3d7a47, 0x6464c8ac, 0x5d5dbae7, 0x1919322b, 0x7373e695,
        0x6060c0a0, 0x81811998, 0x4f4f9ed1, 0xdcdca37f, 0x22224466, 0x2a2a547e, 0x90903bab, 0x88880b83,
        0x46468cca, 0xeeeec729, 0xb8b86bd3, 0x1414283c, 0xdedea779, 0x5e5ebce2, 0x0b0b161d, 0xdbdbad76,
        0xe0e0db3b, 0x32326456, 0x3a3a744e, 0x0a0a141e, 0x494992db, 0x06060c0a, 0x2424486c, 0x5c5cb8e4,
        0xc2c29f5d, 0xd3d3bd6e, 0xacac43ef, 0x6262c4a6, 0x919139a8, 0x959531a4, 0xe4e4d337, 0x7979f28b,
        0xe7e7d532, 0xc8c88b43, 0x37376e59, 0x6d6ddab7, 0x8d8d018c, 0xd5d5b164, 0x4e4e9cd2, 0xa9a949e0,
        0x6c6cd8b4, 0x5656acfa, 0xf4f4f307, 0xeaeacf25, 0x6565caaf, 0x7a7af48e, 0xaeae47e9, 0x08081018,
        0xbaba6fd5, 0x7878f088, 0x25254a6f, 0x2e2e5c72, 0x1c1c3824, 0xa6a657f1, 0xb4b473c7, 0xc6c69751,
        0xe8e8cb23, 0xdddda17c, 0x7474e89c, 0x1f1f3e21, 0x4b4b96dd, 0xbdbd61dc, 0x8b8b0d86, 0x8a8a0f85,
        0x7070e090, 0x3e3e7c42, 0xb5b571c4, 0x6666ccaa, 0x484890d8, 0x03030605, 0xf6f6f701, 0x0e0e1c12,
        0x6161c2a3, 0x35356a5f, 0x5757aef9, 0xb9b969d0, 0x86861791, 0xc1c19958, 0x1d1d3a27, 0x9e9e27b9,
        0xe1e1d938, 0xf8f8eb13, 0x98982bb3, 0x11112233, 0x6969d2bb, 0xd9d9a970, 0x8e8e0789, 0x949433a7,
        0x9b9b2db6, 0x1e1e3c22, 0x87871592, 0xe9e9c920, 0xcece8749, 0x5555aaff, 0x28285078, 0xdfdfa57a,
        0x8c8c038f, 0xa1a159f8, 0x89890980, 0x0d0d1a17, 0xbfbf65da, 0xe6e6d731, 0x424284c6, 0x6868d0b8,
        0x414182c3, 0x999929b0, 0x2d2d5a77, 0x0f0f1e11, 0xb0b07bcb, 0x5454a8fc, 0xbbbb6dd6, 0x16162c3a
    };

    private static final int[] S1_T2 = {
        0x63c6a563, 0x7cf8847c, 0x77ee9977, 0x7bf68d7b, 0xf2ff0df2, 0x6bd6bd6b, 0x6fdeb16f, 0xc59154c5,
        0x30605030, 0x01020301, 0x67cea967, 0x2b567d2b, 0xfee719fe, 0xd7b562d7, 0xab4de6ab, 0x76ec9a76,
        0xca8f45ca, 0x821f9d82, 0xc98940c9, 0x7dfa877d, 0xfaef15fa, 0x59b2eb59, 0x478ec947, 0xf0fb0bf0,
        0xad41ecad, 0xd4b367d4, 0xa25ffda2, 0xaf45eaaf, 0x9c23bf9c, 0xa453f7a4, 0x72e49672, 0xc09b5bc0,
        0xb775c2b7, 0xfde11cfd, 0x933dae93, 0x264c6a26, 0x366c5a36, 0x3f7e413f, 0xf7f502f7, 0xcc834fcc,
        0x34685c34, 0xa551f4a5, 0xe5d134e5, 0xf1f908f1, 0x71e29371, 0xd8ab73d8, 0x31625331, 0x152a3f15,
        0x04080c04, 0xc79552c7, 0x23466523, 0xc39d5ec3, 0x18302818, 0x9637a196, 0x050a0f05, 0x9a2fb59a,
        0x070e0907, 0x12243612, 0x801b9b80, 0xe2df3de2, 0xebcd26eb, 0x274e6927, 0xb27fcdb2, 0x75ea9f75,
        0x09121b09, 0x831d9e83, 0x2c58742c, 0x1a342e1a, 0x1b362d1b, 0x6edcb26e, 0x5ab4ee5a, 0xa05bfba0,
        0x52a4f652, 0x3b764d3b, 0xd6b761d6, 0xb37dceb3, 0x29527b29, 0xe3dd3ee3, 0x2f5e712f, 0x84139784,
        0x53a6f553, 0xd1b968d1, 0x00000000, 0xedc12ced, 0x20406020, 0xfce31ffc, 0xb179c8b1, 0x5bb6ed5b,
        0x6ad4be6a, 0xcb8d46cb, 0xbe67d9be, 0x39724b39, 0x4a94de4a, 0x4c98d44c, 0x58b0e858, 0xcf854acf,
        0xd0bb6bd0, 0xefc52aef, 0xaa4fe5aa, 0xfbed16fb, 0x4386c543, 0x4d9ad74d, 0x33665533, 0x85119485,
        0x458acf45, 0xf9e910f9, 0x02040602, 0x7ffe817f, 0x50a0f050, 0x3c78443c, 0x9f25ba9f, 0xa84be3a8,
        0x51a2f351, 0xa35dfea3, 0x4080c040, 0x8f058a8f, 0x923fad92, 0x9d21bc9d, 0x38704838, 0xf5f104f5,
        0xbc63dfbc, 0xb677c1b6, 0xdaaf75da, 0x21426321, 0x10203010, 0xffe51aff, 0xf3fd0ef3, 0xd2bf6dd2,
        0xcd814ccd, 0x0c18140c, 0x13263513, 0xecc32fec, 0x5fbee15f, 0x9735a297, 0x4488cc44, 0x172e3917,
        0xc49357c4, 0xa755f2a7, 0x7efc827e, 0x3d7a473d, 0x64c8ac64, 0x5dbae75d, 0x19322b19, 0x73e69573,
        0x60c0a060, 0x81199881, 0x4f9ed14f, 0xdca37fdc, 0x22446622, 0x2a547e2a, 0x903bab90, 0x880b8388,
        0x468cca46, 0xeec729ee, 0xb86bd3b8, 0x14283c14, 0xdea779de, 0x5ebce25e, 0x0b161d0b, 0xdbad76db,
        0xe0db3be0, 0x32645632, 0x3a744e3a, 0x0a141e0a, 0x4992db49, 0x060c0a06, 0x24486c24, 0x5cb8e45c,
        0xc29f5dc2, 0xd3bd6ed3, 0xac43efac, 0x62c4a662, 0x9139a891, 0x9531a495, 0xe4d337e4, 0x79f28b79,
        0xe7d532e7, 0xc88b43c8, 0x376e5937, 0x6ddab76d, 0x8d018c8d, 0xd5b164d5, 0x4e9cd24e, 0xa949e0a9,
        0x6cd8b46c, 0x56acfa56, 0xf4f307f4, 0xeacf25ea, 0x65caaf65, 0x7af48e7a, 0xae47e9ae, 0x08101808,
        0xba6fd5ba, 0x78f08878, 0x254a6f25, 0x2e5c722e, 0x1c38241c, 0xa657f1a6, 0xb473c7b4, 0xc69751c6,
        0xe8cb23e8, 0xdda17cdd, 0x74e89c74, 0x1f3e211f, 0x4b96dd4b, 0xbd61dcbd, 0x8b0d868b, 0x8a0f858a,
        0x70e09070, 0x3e7c423e, 0xb571c4b5, 0x66ccaa66, 0x4890d848, 0x03060503, 0xf6f701f6, 0x0e1c120e,
        0x61c2a361, 0x356a5f35, 0x57aef957, 0xb969d0b9, 0x86179186, 0xc19958c1, 0x1d3a271d, 0x9e27b99e,
        0xe1d938e1, 0xf8eb13f8, 0x982bb398, 0x11223311, 0x69d2bb69, 0xd9a970d9, 0x8e07898e, 0x9433a794,
        0x9b2db69b, 0x1e3c221e, 0x87159287, 0xe9c920e9, 0xce8749ce, 0x55aaff55, 0x28507828, 0xdfa57adf,
        0x8c038f8c, 0xa159f8a1, 0x89098089, 0x0d1a170d, 0xbf65dabf, 0xe6d731e6, 0x4284c642, 0x68d0b868,
        0x4182c341, 0x9929b099, 0x2d5a772d, 0x0f1e110f, 0xb07bcbb0, 0x54a8fc54, 0xbb6dd6bb, 0x162c3a16
    };

    private static final int[] S1_T3 = {
        0xc6a56363, 0xf8847c7c, 0xee997777, 0xf68d7b7b, 0xff0df2f2, 0xd6bd6b6b, 0xdeb16f6f, 0x9154c5c5,
        0x60503030, 0x02030101, 0xcea96767, 0x567d2b2b, 0xe719fefe, 0xb562d7d7, 0x4de6abab, 0xec9a7676,
        0x8f45caca, 0x1f9d8282, 0x8940c9c9, 0xfa877d7d, 0xef15fafa, 0xb2eb5959, 0x8ec94747, 0xfb0bf0f0,
        0x41ecadad, 0xb367d4d4, 0x5ffda2a2, 0x45eaafaf, 0x23bf9c9c, 0x53f7a4a4, 0xe4967272, 0x9b5bc0c0,
        0x75c2b7b7, 0xe11cfdfd, 0x3dae9393, 0x4c6a2626, 0x6c5a3636, 0x7e413f3f, 0xf502f7f7, 0x834fcccc,
        0x685c3434, 0x51f4a5a5, 0xd134e5e5, 0xf908f1f1, 0xe2937171, 0xab73d8d8, 0x62533131, 0x2a3f1515,
        0x080c0404, 0x9552c7c7, 0x46652323, 0x9d5ec3c3, 0x30281818, 0x37a19696, 0x0a0f0505, 0x2fb59a9a,
        0x0e090707, 0x24361212, 0x1b9b8080, 0xdf3de2e2, 0xcd26ebeb, 0x4e692727, 0x7fcdb2b2, 0xea9f7575,
        0x121b0909, 0x1d9e8383, 0x58742c2c, 0x342e1a1a, 0x362d1b1b, 0xdcb26e6e, 0xb4ee5a5a, 0x5bfba0a0,
        0xa4f65252, 0x764d3b3b, 0xb761d6d6, 0x7dceb3b3, 0x527b2929, 0xdd3ee3e3, 0x5e712f2f, 0x13978484,
        0xa6f55353, 0xb968d1d1, 0x00000000, 0xc12ceded, 0x40602020, 0xe31ffcfc, 0x79c8b1b1, 0xb6ed5b5b,
        0xd4be6a6a, 0x8d46cbcb, 0x67d9bebe, 0x724b3939, 0x94de4a4a, 0x98d44c4c, 0xb0e85858, 0x854acfcf,
        0xbb6bd0d0, 0xc52aefef, 0x4fe5aaaa, 0xed16fbfb, 0x86c54343, 0x9ad74d4d, 0x66553333, 0x11948585,
        0x8acf4545, 0xe910f9f9, 0x04060202, 0xfe817f7f, 0xa0f05050, 0x78443c3c, 0x25ba9f9f, 0x4be3a8a8,
        0xa2f35151, 0x5dfea3a3, 0x80c04040, 0x058a8f8f, 0x3fad9292, 0x21bc9d9d, 0x70483838, 0xf104f5f5,
        0x63dfbcbc, 0x77c1b6b6, 0xaf75dada, 0x42632121, 0x20301010, 0xe51affff, 0xfd0ef3f3, 0xbf6dd2d2,
        0x814ccdcd, 0x18140c0c, 0x26351313, 0xc32fecec, 0xbee15f5f, 0x35a29797, 0x88cc4444, 0x2e391717,
        0x9357c4c4, 0x55f2a7a7, 0xfc827e7e, 0x7a473d3d, 0xc8ac6464, 0xbae75d5d, 0x322b1919, 0xe6957373,
        0xc0a06060, 0x19988181, 0x9ed14f4f, 0xa37fdcdc, 0x44662222, 0x547e2a2a, 0x3bab9090, 0x0b838888,
        0x8cca4646, 0xc729eeee, 0x6bd3b8b8, 0x283c1414, 0xa779dede, 0xbce25e5e, 0x161d0b0b, 0xad76dbdb,
        0xdb3be0e0, 0x64563232, 0x744e3a3a, 0x141e0a0a, 0x92db4949, 0x0c0a0606, 0x486c2424, 0xb8e45c5c,
        0x9f5dc2c2, 0xbd6ed3d3, 0x43efacac, 0xc4a66262, 0x39a89191, 0x31a49595, 0xd337e4e4, 0xf28b7979,
        0xd532e7e7, 0x8b43c8c8, 0x6e593737, 0xdab76d6d, 0x018c8d8d, 0xb164d5d5, 0x9cd24e4e, 0x49e0a9a9,
        0xd8b46c6c, 0xacfa5656, 0xf307f4f4, 0xcf25eaea, 0xcaaf6565, 0xf48e7a7a, 0x47e9aeae, 0x10180808,
        0x6fd5baba, 0xf0887878, 0x4a6f2525, 0x5c722e2e, 0x38241c1c, 0x57f1a6a6, 0x73c7b4b4, 0x9751c6c6,
        0xcb23e8e8, 0xa17cdddd, 0xe89c7474, 0x3e211f1f, 0x96dd4b4b, 0x61dcbdbd, 0x0d868b8b, 0x0f858a8a,
        0xe0907070, 0x7c423e3e, 0x71c4b5b5, 0xccaa6666, 0x90d84848, 0x06050303, 0xf701f6f6, 0x1c120e0e,
        0xc2a36161, 0x6a5f3535, 0xaef95757, 0x69d0b9b9, 0x17918686, 0x9958c1c1, 0x3a271d1d, 0x27b99e9e,
        0xd938e1e1, 0xeb13f8f8, 0x2bb39898, 0x22331111, 0xd2bb6969, 0xa970d9d9, 0x07898e8e, 0x33a79494,
        0x2db69b9b, 0x3c221e1e, 0x15928787, 0xc920e9e9, 0x8749cece, 0xaaff5555, 0x50782828, 0xa57adfdf,
        0x038f8c8c, 0x59f8a1a1, 0x09808989, 0x1a170d0d, 0x65dabfbf, 0xd731e6e6, 0x84c64242, 0xd0b86868,
        0x82c34141, 0x29b09999, 0x5a772d2d, 0x1e110f0f, 0x7bcbb0b0, 0xa8fc5454, 0x6dd6bbbb, 0x2c3a1616
    };

    private static final int[] S2_T0 = {
        0x6f25254a, 0x6c242448, 0x957373e6, 0xa96767ce, 0x10d7d7c7, 0x9baeae35, 0xe45c5cb8, 0x50303060,
        0x85a4a421, 0x5beeeeb5, 0xb26e6edc, 0x34cbcbff, 0x877d7dfa, 0xb6b5b503, 0xef82826d, 0x04dbdbdf,
        0x45e4e4a1, 0xfb8e8e75, 0xd8484890, 0xdb494992, 0xd14f4f9e, 0xe75d5dba, 0xbe6a6ad4, 0x887878f0,
        0x907070e0, 0xf1888879, 0x51e8e8b9, 0xe15f5fbe, 0xe25e5ebc, 0xe5848461, 0xaf6565ca, 0x4fe2e2ad,
        0x01d8d8d9, 0x52e9e9bb, 0x3dccccf1, 0x5eededb3, 0xc0404080, 0x712f2f5e, 0x33111122, 0x78282850,
        0xf95757ae, 0x1fd2d2cd, 0x9dacac31, 0x4ce3e3af, 0xde4a4a94, 0x3f15152a, 0x2d1b1b36, 0xa2b9b91b,
        0xbfb2b20d, 0xe9808069, 0xe6858563, 0x83a6a625, 0x722e2e5c, 0x06020204, 0xc947478e, 0x7b292952,
        0x0907070e, 0xdd4b4b96, 0x120e0e1c, 0x2ac1c1eb, 0xf35151a2, 0x97aaaa3d, 0xf289897b, 0x15d4d4c1,
        0x37cacafd, 0x03010102, 0xca46468c, 0xbcb3b30f, 0x58efefb7, 0x0eddddd3, 0xcc444488, 0x8d7b7bf6,
        0x2fc2c2ed, 0x817f7ffe, 0xabbebe15, 0x2cc3c3ef, 0xc89f9f57, 0x60202040, 0xd44c4c98, 0xac6464c8,
        0xec83836f, 0x8fa2a22d, 0xb86868d0, 0xc6424284, 0x35131326, 0xb5b4b401, 0xc3414182, 0x3ecdcdf3,
        0xa7baba1d, 0x23c6c6e5, 0xa4bbbb1f, 0xb76d6dda, 0xd74d4d9a, 0x937171e2, 0x63212142, 0x75f4f481,
        0xfe8d8d73, 0xb9b0b009, 0x46e5e5a3, 0xdc93934f, 0x6bfefe95, 0xf88f8f77, 0x43e6e6a5, 0x38cfcff7,
        0xc5434386, 0xcf45458a, 0x53313162, 0x66222244, 0x5937376e, 0x5a36366c, 0xd3969645, 0x67fafa9d,
        0xadbcbc11, 0x110f0f1e, 0x18080810, 0xf65252a4, 0x271d1d3a, 0xff5555aa, 0x2e1a1a34, 0x26c5c5e3,
        0xd24e4e9c, 0x65232346, 0xbb6969d2, 0x8e7a7af4, 0xdf92924d, 0x68ffff97, 0xed5b5bb6, 0xee5a5ab4,
        0x54ebebbf, 0xc79a9a5d, 0x241c1c38, 0x92a9a93b, 0x1ad1d1cb, 0x827e7efc, 0x170d0d1a, 0x6dfcfc91,
        0xf05050a0, 0xf78a8a7d, 0xb3b6b605, 0xa66262c4, 0x76f5f583, 0x1e0a0a14, 0x61f8f899, 0x0ddcdcd1,
        0x05030306, 0x443c3c78, 0x140c0c18, 0x4b393972, 0x7af1f18b, 0xa1b8b819, 0x7cf3f38f, 0x473d3d7a,
        0x7ff2f28d, 0x16d5d5c3, 0xd0979747, 0xaa6666cc, 0xea81816b, 0x56323264, 0x89a0a029, 0x00000000,
        0x0a06060c, 0x3bcecef5, 0x73f6f685, 0x57eaeabd, 0xb0b7b707, 0x3917172e, 0x70f7f787, 0xfd8c8c71,
        0x8b7979f2, 0x13d6d6c5, 0x80a7a727, 0xa8bfbf17, 0xf48b8b7f, 0x413f3f7e, 0x211f1f3e, 0xf55353a6,
        0xa56363c6, 0x9f7575ea, 0x5f35356a, 0x742c2c58, 0xa06060c0, 0x6efdfd93, 0x6927274e, 0x1cd3d3cf,
        0xd5949441, 0x86a5a523, 0x847c7cf8, 0x8aa1a12b, 0x0f05050a, 0xe85858b0, 0x772d2d5a, 0xaebdbd13,
        0x02d9d9db, 0x20c7c7e7, 0x98afaf37, 0xbd6b6bd6, 0xfc5454a8, 0x1d0b0b16, 0x49e0e0a9, 0x48383870,
        0x0c040408, 0x31c8c8f9, 0xce9d9d53, 0x40e7e7a7, 0x3c141428, 0xbab1b10b, 0xe0878767, 0xcd9c9c51,
        0x08dfdfd7, 0xb16f6fde, 0x62f9f99b, 0x07dadadd, 0x7e2a2a54, 0x25c4c4e1, 0xeb5959b2, 0x3a16162c,
        0x9c7474e8, 0xda91914b, 0x94abab3f, 0x6a26264c, 0xa36161c2, 0x9a7676ec, 0x5c343468, 0x7d2b2b56,
        0x9eadad33, 0xc299995b, 0x64fbfb9f, 0x967272e4, 0x5dececb1, 0x55333366, 0x36121224, 0x0bdeded5,
        0xc1989859, 0x4d3b3b76, 0x29c0c0e9, 0xc49b9b5f, 0x423e3e7c, 0x28181830, 0x30101020, 0x4e3a3a74,
        0xfa5656ac, 0x4ae1e1ab, 0x997777ee, 0x32c9c9fb, 0x221e1e3c, 0xcb9e9e55, 0xd6959543, 0x8ca3a32f,
        0xd9909049, 0x2b191932, 0x91a8a839, 0xb46c6cd8, 0x1b090912, 0x19d0d0c9, 0x79f0f089, 0xe3868665
    };

    private static final int[] S2_T1 = {
        0x25254a6f, 0x2424486c, 0x7373e695, 0x6767cea9, 0xd7d7c710, 0xaeae359b, 0x5c5cb8e4, 0x30306050,
        0xa4a42185, 0xeeeeb55b, 0x6e6edcb2, 0xcbcbff34, 0x7d7dfa87, 0xb5b503b6, 0x82826def, 0xdbdbdf04,
        0xe4e4a145, 0x8e8e75fb, 0x484890d8, 0x494992db, 0x4f4f9ed1, 0x5d5dbae7, 0x6a6ad4be, 0x7878f088,
        0x7070e090, 0x888879f1, 0xe8e8b951, 0x5f5fbee1, 0x5e5ebce2, 0x848461e5, 0x6565caaf, 0xe2e2ad4f,
        0xd8d8d901, 0xe9e9bb52, 0xccccf13d, 0xededb35e, 0x404080c0, 0x2f2f5e71, 0x11112233, 0x28285078,
        0x5757aef9, 0xd2d2cd1f, 0xacac319d, 0xe3e3af4c, 0x4a4a94de, 0x15152a3f, 0x1b1b362d, 0xb9b91ba2,
        0xb2b20dbf, 0x808069e9, 0x858563e6, 0xa6a62583, 0x2e2e5c72, 0x02020406, 0x47478ec9, 0x2929527b,
        0x07070e09, 0x4b4b96dd, 0x0e0e1c12, 0xc1c1eb2a, 0x5151a2f3, 0xaaaa3d97, 0x89897bf2, 0xd4d4c115,
        0xcacafd37, 0x01010203, 0x46468cca, 0xb3b30fbc, 0xefefb758, 0xddddd30e, 0x444488cc, 0x7b7bf68d,
        0xc2c2ed2f, 0x7f7ffe81, 0xbebe15ab, 0xc3c3ef2c, 0x9f9f57c8, 0x20204060, 0x4c4c98d4, 0x6464c8ac,
        0x83836fec, 0xa2a22d8f, 0x6868d0b8, 0x424284c6, 0x13132635, 0xb4b401b5, 0x414182c3, 0xcdcdf33e,
        0xbaba1da7, 0xc6c6e523, 0xbbbb1fa4, 0x6d6ddab7, 0x4d4d9ad7, 0x7171e293, 0x21214263, 0xf4f48175,
        0x8d8d73fe, 0xb0b009b9, 0xe5e5a346, 0x93934fdc, 0xfefe956b, 0x8f8f77f8, 0xe6e6a543, 0xcfcff738,
        0x434386c5, 0x45458acf, 0x31316253, 0x22224466, 0x37376e59, 0x36366c5a, 0x969645d3, 0xfafa9d67,
        0xbcbc11ad, 0x0f0f1e11, 0x08081018, 0x5252a4f6, 0x1d1d3a27, 0x5555aaff, 0x1a1a342e, 0xc5c5e326,
        0x4e4e9cd2, 0x23234665, 0x6969d2bb, 0x7a7af48e, 0x92924ddf, 0xffff9768, 0x5b5bb6ed, 0x5a5ab4ee,
        0xebebbf54, 0x9a9a5dc7, 0x1c1c3824, 0xa9a93b92, 0xd1d1cb1a, 0x7e7efc82, 0x0d0d1a17, 0xfcfc916d,
        0x5050a0f0, 0x8a8a7df7, 0xb6b605b3, 0x6262c4a6, 0xf5f58376, 0x0a0a141e, 0xf8f89961, 0xdcdcd10d,
        0x03030605, 0x3c3c7844, 0x0c0c1814, 0x3939724b, 0xf1f18b7a, 0xb8b819a1, 0xf3f38f7c, 0x3d3d7a47,
        0xf2f28d7f, 0xd5d5c316, 0x979747d0, 0x6666ccaa, 0x81816bea, 0x32326456, 0xa0a02989, 0x00000000,
        0x06060c0a, 0xcecef53b, 0xf6f68573, 0xeaeabd57, 0xb7b707b0, 0x17172e39, 0xf7f78770, 0x8c8c71fd,
        0x7979f28b, 0xd6d6c513, 0xa7a72780, 0xbfbf17a8, 0x8b8b7ff4, 0x3f3f7e41, 0x1f1f3e21, 0x5353a6f5,
        0x6363c6a5, 0x7575ea9f, 0x35356a5f, 0x2c2c5874, 0x6060c0a0, 0xfdfd936e, 0x27274e69, 0xd3d3cf1c,
        0x949441d5, 0xa5a52386, 0x7c7cf884, 0xa1a12b8a, 0x05050a0f, 0x5858b0e8, 0x2d2d5a77, 0xbdbd13ae,
        0xd9d9db02, 0xc7c7e720, 0xafaf3798, 0x6b6bd6bd, 0x5454a8fc, 0x0b0b161d, 0xe0e0a949, 0x38387048,
        0x0404080c, 0xc8c8f931, 0x9d9d53ce, 0xe7e7a740, 0x1414283c, 0xb1b10bba, 0x878767e0, 0x9c9c51cd,
        0xdfdfd708, 0x6f6fdeb1, 0xf9f99b62, 0xdadadd07, 0x2a2a547e, 0xc4c4e125, 0x5959b2eb, 0x16162c3a,
        0x7474e89c, 0x91914bda, 0xabab3f94, 0x26264c6a, 0x6161c2a3, 0x7676ec9a, 0x3434685c, 0x2b2b567d,
        0xadad339e, 0x99995bc2, 0xfbfb9f64, 0x7272e496, 0xececb15d, 0x33336655, 0x12122436, 0xdeded50b,
        0x989859c1, 0x3b3b764d, 0xc0c0e929, 0x9b9b5fc4, 0x3e3e7c42, 0x18183028, 0x10102030, 0x3a3a744e,
        0x5656acfa, 0xe1e1ab4a, 0x7777ee99, 0xc9c9fb32, 0x1e1e3c22, 0x9e9e55cb, 0x959543d6, 0xa3a32f8c,
        0x909049d9, 0x1919322b, 0xa8a83991, 0x6c6cd8b4, 0x0909121b, 0xd0d0c919, 0xf0f08979, 0x868665e3
    };

    private static final int[] S2_T2 = {
        0x254a6f25, 0x24486c24, 0x73e69573, 0x67cea967, 0xd7c710d7, 0xae359bae, 0x5cb8e45c, 0x30605030,
        0xa42185a4, 0xeeb55bee, 0x6edcb26e, 0xcbff34cb, 0x7dfa877d, 0xb503b6b5, 0x826def82, 0xdbdf04db,
        0xe4a145e4, 0x8e75fb8e, 0x4890d848, 0x4992db49, 0x4f9ed14f, 0x5dbae75d, 0x6ad4be6a, 0x78f08878,
        0x70e09070, 0x8879f188, 0xe8b951e8, 0x5fbee15f, 0x5ebce25e, 0x8461e584, 0x65caaf65, 0xe2ad4fe2,
        0xd8d901d8, 0xe9bb52e9, 0xccf13dcc, 0xedb35eed, 0x4080c040, 0x2f5e712f, 0x11223311, 0x28507828,
        0x57aef957, 0xd2cd1fd2, 0xac319dac, 0xe3af4ce3, 0x4a94de4a, 0x152a3f15, 0x1b362d1b, 0xb91ba2b9,
        0xb20dbfb2, 0x8069e980, 0x8563e685, 0xa62583a6, 0x2e5c722e, 0x02040602, 0x478ec947, 0x29527b29,
        0x070e0907, 0x4b96dd4b, 0x0e1c120e, 0xc1eb2ac1, 0x51a2f351, 0xaa3d97aa, 0x897bf289, 0xd4c115d4,
        0xcafd37ca, 0x01020301, 0x468cca46, 0xb30fbcb3, 0xefb758ef, 0xddd30edd, 0x4488cc44, 0x7bf68d7b,
        0xc2ed2fc2, 0x7ffe817f, 0xbe15abbe, 0xc3ef2cc3, 0x9f57c89f, 0x20406020, 0x4c98d44c, 0x64c8ac64,
        0x836fec83, 0xa22d8fa2, 0x68d0b868, 0x4284c642, 0x13263513, 0xb401b5b4, 0x4182c341, 0xcdf33ecd,
        0xba1da7ba, 0xc6e523c6, 0xbb1fa4bb, 0x6ddab76d, 0x4d9ad74d, 0x71e29371, 0x21426321, 0xf48175f4,
        0x8d73fe8d, 0xb009b9b0, 0xe5a346e5, 0x934fdc93, 0xfe956bfe, 0x8f77f88f, 0xe6a543e6, 0xcff738cf,
        0x4386c543, 0x458acf45, 0x31625331, 0x22446622, 0x376e5937, 0x366c5a36, 0x9645d396, 0xfa9d67fa,
        0xbc11adbc, 0x0f1e110f, 0x08101808, 0x52a4f652, 0x1d3a271d, 0x55aaff55, 0x1a342e1a, 0xc5e326c5,
        0x4e9cd24e, 0x23466523, 0x69d2bb69, 0x7af48e7a, 0x924ddf92, 0xff9768ff, 0x5bb6ed5b, 0x5ab4ee5a,
        0xebbf54eb, 0x9a5dc79a, 0x1c38241c, 0xa93b92a9, 0xd1cb1ad1, 0x7efc827e, 0x0d1a170d, 0xfc916dfc,
        0x50a0f050, 0x8a7df78a, 0xb605b3b6, 0x62c4a662, 0xf58376f5, 0x0a141e0a, 0xf89961f8, 0xdcd10ddc,
        0x03060503, 0x3c78443c, 0x0c18140c, 0x39724b39, 0xf18b7af1, 0xb819a1b8, 0xf38f7cf3, 0x3d7a473d,
        0xf28d7ff2, 0xd5c316d5, 0x9747d097, 0x66ccaa66, 0x816bea81, 0x32645632, 0xa02989a0, 0x00000000,
        0x060c0a06, 0xcef53bce, 0xf68573f6, 0xeabd57ea, 0xb707b0b7, 0x172e3917, 0xf78770f7, 0x8c71fd8c,
        0x79f28b79, 0xd6c513d6, 0xa72780a7, 0xbf17a8bf, 0x8b7ff48b, 0x3f7e413f, 0x1f3e211f, 0x53a6f553,
        0x63c6a563, 0x75ea9f75, 0x356a5f35, 0x2c58742c, 0x60c0a060, 0xfd936efd, 0x274e6927, 0xd3cf1cd3,
        0x9441d594, 0xa52386a5, 0x7cf8847c, 0xa12b8aa1, 0x050a0f05, 0x58b0e858, 0x2d5a772d, 0xbd13aebd,
        0xd9db02d9, 0xc7e720c7, 0xaf3798af, 0x6bd6bd6b, 0x54a8fc54, 0x0b161d0b, 0xe0a949e0, 0x38704838,
        0x04080c04, 0xc8f931c8, 0x9d53ce9d, 0xe7a740e7, 0x14283c14, 0xb10bbab1, 0x8767e087, 0x9c51cd9c,
        0xdfd708df, 0x6fdeb16f, 0xf99b62f9, 0xdadd07da, 0x2a547e2a, 0xc4e125c4, 0x59b2eb59, 0x162c3a16,
        0x74e89c74, 0x914bda91, 0xab3f94ab, 0x264c6a26, 0x61c2a361, 0x76ec9a76, 0x34685c34, 0x2b567d2b,
        0xad339ead, 0x995bc299, 0xfb9f64fb, 0x72e49672, 0xecb15dec, 0x33665533, 0x12243612, 0xded50bde,
        0x9859c198, 0x3b764d3b, 0xc0e929c0, 0x9b5fc49b, 0x3e7c423e, 0x18302818, 0x10203010, 0x3a744e3a,
        0x56acfa56, 0xe1ab4ae1, 0x77ee9977, 0xc9fb32c9, 0x1e3c221e, 0x9e55cb9e, 0x9543d695, 0xa32f8ca3,
        0x9049d990, 0x19322b19, 0xa83991a8, 0x6cd8b46c, 0x09121b09, 0xd0c919d0, 0xf08979f0, 0x8665e386
    };

    private static final int[] S2_T3 = {
        0x4a6f2525, 0x486c2424, 0xe6957373, 0xcea96767, 0xc710d7d7, 0x359baeae, 0xb8e45c5c, 0x60503030,
        0x2185a4a4, 0xb55beeee, 0xdcb26e6e, 0xff34cbcb, 0xfa877d7d, 0x03b6b5b5, 0x6def8282, 0xdf04dbdb,
        0xa145e4e4, 0x75fb8e8e, 0x90d84848, 0x92db4949, 0x9ed14f4f, 0xbae75d5d, 0xd4be6a6a, 0xf0887878,
        0xe0907070, 0x79f18888, 0xb951e8e8, 0xbee15f5f, 0xbce25e5e, 0x61e58484, 0xcaaf6565, 0xad4fe2e2,
        0xd901d8d8, 0xbb52e9e9, 0xf13dcccc, 0xb35eeded, 0x80c04040, 0x5e712f2f, 0x22331111, 0x50782828,
        0xaef95757, 0xcd1fd2d2, 0x319dacac, 0xaf4ce3e3, 0x94de4a4a, 0x2a3f1515, 0x362d1b1b, 0x1ba2b9b9,
        0x0dbfb2b2, 0x69e98080, 0x63e68585, 0x2583a6a6, 0x5c722e2e, 0x04060202, 0x8ec94747, 0x527b2929,
        0x0e090707, 0x96dd4b4b, 0x1c120e0e, 0xeb2ac1c1, 0xa2f35151, 0x3d97aaaa, 0x7bf28989, 0xc115d4d4,
        0xfd37caca, 0x02030101, 0x8cca4646, 0x0fbcb3b3, 0xb758efef, 0xd30edddd, 0x88cc4444, 0xf68d7b7b,
        0xed2fc2c2, 0xfe817f7f, 0x15abbebe, 0xef2cc3c3, 0x57c89f9f, 0x40602020, 0x98d44c4c, 0xc8ac6464,
        0x6fec8383, 0x2d8fa2a2, 0xd0b86868, 0x84c64242, 0x26351313, 0x01b5b4b4, 0x82c34141, 0xf33ecdcd,
        0x1da7baba, 0xe523c6c6, 0x1fa4bbbb, 0xdab76d6d, 0x9ad74d4d, 0xe2937171, 0x42632121, 0x8175f4f4,
        0x73fe8d8d, 0x09b9b0b0, 0xa346e5e5, 0x4fdc9393, 0x956bfefe, 0x77f88f8f, 0xa543e6e6, 0xf738cfcf,
        0x86c54343, 0x8acf4545, 0x62533131, 0x44662222, 0x6e593737, 0x6c5a3636, 0x45d39696, 0x9d67fafa,
        0x11adbcbc, 0x1e110f0f, 0x10180808, 0xa4f65252, 0x3a271d1d, 0xaaff5555, 0x342e1a1a, 0xe326c5c5,
        0x9cd24e4e, 0x46652323, 0xd2bb6969, 0xf48e7a7a, 0x4ddf9292, 0x9768ffff, 0xb6ed5b5b, 0xb4ee5a5a,
        0xbf54ebeb, 0x5dc79a9a, 0x38241c1c, 0x3b92a9a9, 0xcb1ad1d1, 0xfc827e7e, 0x1a170d0d, 0x916dfcfc,
        0xa0f05050, 0x7df78a8a, 0x05b3b6b6, 0xc4a66262, 0x8376f5f5, 0x141e0a0a, 0x9961f8f8, 0xd10ddcdc,
        0x06050303, 0x78443c3c, 0x18140c0c, 0x724b3939, 0x8b7af1f1, 0x19a1b8b8, 0x8f7cf3f3, 0x7a473d3d,
        0x8d7ff2f2, 0xc316d5d5, 0x47d09797, 0xccaa6666, 0x6bea8181, 0x64563232, 0x2989a0a0, 0x00000000,
        0x0c0a0606, 0xf53bcece, 0x8573f6f6, 0xbd57eaea, 0x07b0b7b7, 0x2e391717, 0x8770f7f7, 0x71fd8c8c,
        0xf28b7979, 0xc513d6d6, 0x2780a7a7, 0x17a8bfbf, 0x7ff48b8b, 0x7e413f3f, 0x3e211f1f, 0xa6f55353,
        0xc6a56363, 0xea9f7575, 0x6a5f3535, 0x58742c2c, 0xc0a06060, 0x936efdfd, 0x4e692727, 0xcf1cd3d3,
        0x41d59494, 0x2386a5a5, 0xf8847c7c, 0x2b8aa1a1, 0x0a0f0505, 0xb0e85858, 0x5a772d2d, 0x13aebdbd,
        0xdb02d9d9, 0xe720c7c7, 0x3798afaf, 0xd6bd6b6b, 0xa8fc5454, 0x161d0b0b, 0xa949e0e0, 0x70483838,
        0x080c0404, 0xf931c8c8, 0x53ce9d9d, 0xa740e7e7, 0x283c1414, 0x0bbab1b1, 0x67e08787, 0x51cd9c9c,
        0xd708dfdf, 0xdeb16f6f, 0x9b62f9f9, 0xdd07dada, 0x547e2a2a, 0xe125c4c4, 0xb2eb5959, 0x2c3a1616,
        0xe89c7474, 0x4bda9191, 0x3f94abab, 0x4c6a2626, 0xc2a36161, 0xec9a7676, 0x685c3434, 0x567d2b2b,
        0x339eadad, 0x5bc29999, 0x9f64fbfb, 0xe4967272, 0xb15decec, 0x66553333, 0x24361212, 0xd50bdede,
        0x59c19898, 0x764d3b3b, 0xe929c0c0, 0x5fc49b9b, 0x7c423e3e, 0x30281818, 0x20301010, 0x744e3a3a,
        0xacfa5656, 0xab4ae1e1, 0xee997777, 0xfb32c9c9, 0x3c221e1e, 0x55cb9e9e, 0x43d69595, 0x2f8ca3a3,
        0x49d99090, 0x322b1919, 0x3991a8a8, 0xd8b46c6c, 0x121b0909, 0xc919d0d0, 0x8979f0f0, 0x65e38686
    };

    private static final int[] MUL_ALPHA = {
        0x00000000, 0xe19fcf13, 0x6b973726, 0x8a08f835, 0xd6876e4c, 0x3718a15f, 0xbd10596a, 0x5c8f9679,
        0x05a7dc98, 0xe438138b, 0x6e30ebbe, 0x8faf24ad, 0xd320b2d4, 0x32bf7dc7, 0xb8b785f2, 0x59284ae1,
        0x0ae71199, 0xeb78de8a, 0x617026bf, 0x80efe9ac, 0xdc607fd5, 0x3dffb0c6, 0xb7f748f3, 0x566887e0,
        0x0f40cd01, 0xeedf0212, 0x64d7fa27, 0x85483534, 0xd9c7a34d, 0x38586c5e, 0xb250946b, 0x53cf5b78,
        0x1467229b, 0xf5f8ed88, 0x7ff015bd, 0x9e6fdaae, 0xc2e04cd7, 0x237f83c4, 0xa9777bf1, 0x48e8b4e2,
        0x11c0fe03, 0xf05f3110, 0x7a57c925, 0x9bc80636, 0xc747904f, 0x26d85f5c, 0xacd0a769, 0x4d4f687a,
        0x1e803302, 0xff1ffc11, 0x75170424, 0x9488cb37, 0xc8075d4e, 0x2998925d, 0xa3906a68, 0x420fa57b,
        0x1b27ef9a, 0xfab82089, 0x70b0d8bc, 0x912f17af, 0xcda081d6, 0x2c3f4ec5, 0xa637b6f0, 0x47a879e3,
        0x28ce449f, 0xc9518b8c, 0x435973b9, 0xa2c6bcaa, 0xfe492ad3, 0x1fd6e5c0, 0x95de1df5, 0x7441d2e6,
        0x2d699807, 0xccf65714, 0x46feaf21, 0xa7616032, 0xfbeef64b, 0x1a713958, 0x9079c16d, 0x71e60e7e,
        0x22295506, 0xc3b69a15, 0x49be6220, 0xa821ad33, 0xf4ae3b4a, 0x1531f459, 0x9f390c6c, 0x7ea6c37f,
        0x278e899e, 0xc611468d, 0x4c19beb8, 0xad8671ab, 0xf109e7d2, 0x109628c1, 0x9a9ed0f4, 0x7b011fe7,
        0x3ca96604, 0xdd36a917, 0x573e5122, 0xb6a19e31, 0xea2e0848, 0x0bb1c75b, 0x81b93f6e, 0x6026f07d,
        0x390eba9c, 0xd891758f, 0x52998dba, 0xb30642a9, 0xef89d4d0, 0x0e161bc3, 0x841ee3f6, 0x65812ce5,
        0x364e779d, 0xd7d1b88e, 0x5dd940bb, 0xbc468fa8, 0xe0c919d1, 0x0156d6c2, 0x8b5e2ef7, 0x6ac1e1e4,
        0x33e9ab05, 0xd2766416, 0x587e9c23, 0xb9e15330, 0xe56ec549, 0x04f10a5a, 0x8ef9f26f, 0x6f663d7c,
        0x50358897, 0xb1aa4784, 0x3ba2bfb1, 0xda3d70a2, 0x86b2e6db, 0x672d29c8, 0xed25d1fd, 0x0cba1eee,
        0x5592540f, 0xb40d9b1c, 0x3e056329, 0xdf9aac3a, 0x83153a43, 0x628af550, 0xe8820d65, 0x091dc276,
        0x5ad2990e, 0xbb4d561d, 0x3145ae28, 0xd0da613b, 0x8c55f742, 0x6dca3851, 0xe7c2c064, 0x065d0f77,
        0x5f754596, 0xbeea8a85, 0x34e272b0, 0xd57dbda3, 0x89f22bda, 0x686de4c9, 0xe2651cfc, 0x03fad3ef,
        0x4452aa0c, 0xa5cd651f, 0x2fc59d2a, 0xce5a5239, 0x92d5c440, 0x734a0b53, 0xf942f366, 0x18dd3c75,
        0x41f57694, 0xa06ab987, 0x2a6241b2, 0xcbfd8ea1, 0x977218d8, 0x76edd7cb, 0xfce52ffe, 0x1d7ae0ed,
        0x4eb5bb95, 0xaf2a7486, 0x25228cb3, 0xc4bd43a0, 0x9832d5d9, 0x79ad1aca, 0xf3a5e2ff, 0x123a2dec,
        0x4b12670d, 0xaa8da81e, 0x2085502b, 0xc11a9f38, 0x9d950941, 0x7c0ac652, 0xf6023e67, 0x179df174,
        0x78fbcc08, 0x9964031b, 0x136cfb2e, 0xf2f3343d, 0xae7ca244, 0x4fe36d57, 0xc5eb9562, 0x24745a71,
        0x7d5c1090, 0x9cc3df83, 0x16cb27b6, 0xf754e8a5, 0xabdb7edc, 0x4a44b1cf, 0xc04c49fa, 0x21d386e9,
        0x721cdd91, 0x93831282, 0x198beab7, 0xf81425a4, 0xa49bb3dd, 0x45047cce, 0xcf0c84fb, 0x2e934be8,
        0x77bb0109, 0x9624ce1a, 0x1c2c362f, 0xfdb3f93c, 0xa13c6f45, 0x40a3a056, 0xcaab5863, 0x2b349770,
        0x6c9cee93, 0x8d032180, 0x070bd9b5, 0xe69416a6, 0xba1b80df, 0x5b844fcc, 0xd18cb7f9, 0x301378ea,
        0x693b320b, 0x88a4fd18, 0x02ac052d, 0xe333ca3e, 0xbfbc5c47, 0x5e239354, 0xd42b6b61, 0x35b4a472,
        0x667bff0a, 0x87e43019, 0x0decc82c, 0xec73073f, 0xb0fc9146, 0x51635e55, 0xdb6ba660, 0x3af46973,
        0x63dc2392, 0x8243ec81, 0x084b14b4, 0xe9d4dba7, 0xb55b4dde, 0x54c482cd, 0xdecc7af8, 0x3f53b5eb
    };

    private static final int[] DIV_ALPHA = {
        0x00000000, 0x180f40cd, 0x301e8033, 0x2811c0fe, 0x603ca966, 0x7833e9ab, 0x50222955, 0x482d6998,
        0xc078fbcc, 0xd877bb01, 0xf0667bff, 0xe8693b32, 0xa04452aa, 0xb84b1267, 0x905ad299, 0x88559254,
        0x29f05f31, 0x31ff1ffc, 0x19eedf02, 0x01e19fcf, 0x49ccf657, 0x51c3b69a, 0x79d27664, 0x61dd36a9,
        0xe988a4fd, 0xf187e430, 0xd99624ce, 0xc1996403, 0x89b40d9b, 0x91bb4d56, 0xb9aa8da8, 0xa1a5cd65,
        0x5249be62, 0x4a46feaf, 0x62573e51, 0x7a587e9c, 0x32751704, 0x2a7a57c9, 0x026b9737, 0x1a64d7fa,
        0x923145ae, 0x8a3e0563, 0xa22fc59d, 0xba208550, 0xf20decc8, 0xea02ac05, 0xc2136cfb, 0xda1c2c36,
        0x7bb9e153, 0x63b6a19e, 0x4ba76160, 0x53a821ad, 0x1b854835, 0x038a08f8, 0x2b9bc806, 0x339488cb,
        0xbbc11a9f, 0xa3ce5a52, 0x8bdf9aac, 0x93d0da61, 0xdbfdb3f9, 0xc3f2f334, 0xebe333ca, 0xf3ec7307,
        0xa492d5c4, 0xbc9d9509, 0x948c55f7, 0x8c83153a, 0xc4ae7ca2, 0xdca13c6f, 0xf4b0fc91, 0xecbfbc5c,
        0x64ea2e08, 0x7ce56ec5, 0x54f4ae3b, 0x4cfbeef6, 0x04d6876e, 0x1cd9c7a3, 0x34c8075d, 0x2cc74790,
        0x8d628af5, 0x956dca38, 0xbd7c0ac6, 0xa5734a0b, 0xed5e2393, 0xf551635e, 0xdd40a3a0, 0xc54fe36d,
        0x4d1a7139, 0x551531f4, 0x7d04f10a, 0x650bb1c7, 0x2d26d85f, 0x35299892, 0x1d38586c, 0x053718a1,
        0xf6db6ba6, 0xeed42b6b, 0xc6c5eb95, 0xdecaab58, 0x96e7c2c0, 0x8ee8820d, 0xa6f942f3, 0xbef6023e,
        0x36a3906a, 0x2eacd0a7, 0x06bd1059, 0x1eb25094, 0x569f390c, 0x4e9079c1, 0x6681b93f, 0x7e8ef9f2,
        0xdf2b3497, 0xc724745a, 0xef35b4a4, 0xf73af469, 0xbf179df1, 0xa718dd3c, 0x8f091dc2, 0x97065d0f,
        0x1f53cf5b, 0x075c8f96, 0x2f4d4f68, 0x37420fa5, 0x7f6f663d, 0x676026f0, 0x4f71e60e, 0x577ea6c3,
        0xe18d0321, 0xf98243ec, 0xd1938312, 0xc99cc3df, 0x81b1aa47, 0x99beea8a, 0xb1af2a74, 0xa9a06ab9,
        0x21f5f8ed, 0x39fab820, 0x11eb78de, 0x09e43813, 0x41c9518b, 0x59c61146, 0x71d7d1b8, 0x69d89175,
        0xc87d5c10, 0xd0721cdd, 0xf863dc23, 0xe06c9cee, 0xa841f576, 0xb04eb5bb, 0x985f7545, 0x80503588,
        0x0805a7dc, 0x100ae711, 0x381b27ef, 0x20146722, 0x68390eba, 0x70364e77, 0x58278e89, 0x4028ce44,
        0xb3c4bd43, 0xabcbfd8e, 0x83da3d70, 0x9bd57dbd, 0xd3f81425, 0xcbf754e8, 0xe3e69416, 0xfbe9d4db,
        0x73bc468f, 0x6bb30642, 0x43a2c6bc, 0x5bad8671, 0x1380efe9, 0x0b8faf24, 0x239e6fda, 0x3b912f17,
        0x9a34e272, 0x823ba2bf, 0xaa2a6241, 0xb225228c, 0xfa084b14, 0xe2070bd9, 0xca16cb27, 0xd2198bea,
        0x5a4c19be, 0x42435973, 0x6a52998d, 0x725dd940, 0x3a70b0d8, 0x227ff015, 0x0a6e30eb, 0x12617026,
        0x451fd6e5, 0x5d109628, 0x750156d6, 0x6d0e161b, 0x25237f83, 0x3d2c3f4e, 0x153dffb0, 0x0d32bf7d,
        0x85672d29, 0x9d686de4, 0xb579ad1a, 0xad76edd7, 0xe55b844f, 0xfd54c482, 0xd545047c, 0xcd4a44b1,
        0x6cef89d4, 0x74e0c919, 0x5cf109e7, 0x44fe492a, 0x0cd320b2, 0x14dc607f, 0x3ccda081, 0x24c2e04c,
        0xac977218, 0xb49832d5, 0x9c89f22b, 0x8486b2e6, 0xccabdb7e, 0xd4a49bb3, 0xfcb55b4d, 0xe4ba1b80,
        0x17566887, 0x0f59284a, 0x2748e8b4, 0x3f47a879, 0x776ac1e1, 0x6f65812c, 0x477441d2, 0x5f7b011f,
        0xd72e934b, 0xcf21d386, 0xe7301378, 0xff3f53b5, 0xb7123a2d, 0xaf1d7ae0, 0x870cba1e, 0x9f03fad3,
        0x3ea637b6, 0x26a9777b, 0x0eb8b785, 0x16b7f748, 0x5e9a9ed0, 0x4695de1d, 0x6e841ee3, 0x768b5e2e,
        0xfedecc7a, 0xe6d18cb7, 0xcec04c49, 0xd6cf0c84, 0x9ee2651c, 0x86ed25d1, 0xaefce52f, 0xb6f3a5e2
    };

    private static int s1(int w) {
        return S1_T0[(w) & 0xff] ^ S1_T1[(w >>> 8) & 0xff] ^ S1_T2[(w >>> 16) & 0xff] ^ S1_T3[(w >>> 24)];
    }

    private static int s2(int w) {
        return S2_T0[(w) & 0xff] ^ S2_T1[(w >>> 8) & 0xff] ^ S2_T2[(w >>> 16) & 0xff] ^ S2_T3[(w >>> 24)];
    }

    private static void keystream(int[] state, int[] register, int[] buffer) {
        int state0 = state[0], state1 = state[1], state2 = state[2], state3 = state[3];
        int state4 = state[4], state5 = state[5], state6 = state[6], state7 = state[7];
        int state8 = state[8], state9 = state[9], state10 = state[10], state11 = state[11];
        int state12 = state[12], state13 = state[13], state14 = state[14], state15 = state[15];

        int r0 = register[0], r1 = register[1], r2 = register[2];

        int temp0, temp1;

        buffer[0] = (state15 + r0) ^ r1 ^ state0;

        temp0 = r1 + (r2 ^ state5);
        r2 = s2(r1);
        r1 = s1(r0);

        int state16 = (state0 << 8) ^ MUL_ALPHA[state0 >>> 24] ^ state2 ^ (state11 >>> 8) ^ DIV_ALPHA[state11 & 0xff];

        buffer[1] = (state16 + temp0) ^ r1 ^ state1;

        temp1 = r1 + (r2 ^ state6);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state17 = (state1 << 8) ^ MUL_ALPHA[state1 >>> 24] ^ state3 ^ (state12 >>> 8) ^ DIV_ALPHA[state12 & 0xff];

        buffer[2] = (state17 + temp1) ^ r1 ^ state2;

        temp0 = r1 + (r2 ^ state7);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state18 = (state2 << 8) ^ MUL_ALPHA[state2 >>> 24] ^ state4 ^ (state13 >>> 8) ^ DIV_ALPHA[state13 & 0xff];

        buffer[3] = (state18 + temp0) ^ r1 ^ state3;

        temp1 = r1 + (r2 ^ state8);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state19 = (state3 << 8) ^ MUL_ALPHA[state3 >>> 24] ^ state5 ^ (state14 >>> 8) ^ DIV_ALPHA[state14 & 0xff];

        buffer[4] = (state19 + temp1) ^ r1 ^ state4;

        temp0 = r1 + (r2 ^ state9);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state20 = (state4 << 8) ^ MUL_ALPHA[state4 >>> 24] ^ state6 ^ (state15 >>> 8) ^ DIV_ALPHA[state15 & 0xff];

        buffer[5] = (state20 + temp0) ^ r1 ^ state5;

        temp1 = r1 + (r2 ^ state10);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state21 = (state5 << 8) ^ MUL_ALPHA[state5 >>> 24] ^ state7 ^ (state16 >>> 8) ^ DIV_ALPHA[state16 & 0xff];

        buffer[6] = (state21 + temp1) ^ r1 ^ state6;

        temp0 = r1 + (r2 ^ state11);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state22 = (state6 << 8) ^ MUL_ALPHA[state6 >>> 24] ^ state8 ^ (state17 >>> 8) ^ DIV_ALPHA[state17 & 0xff];

        buffer[7] = (state22 + temp0) ^ r1 ^ state7;

        temp1 = r1 + (r2 ^ state12);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state23 = (state7 << 8) ^ MUL_ALPHA[state7 >>> 24] ^ state9 ^ (state18 >>> 8) ^ DIV_ALPHA[state18 & 0xff];

        buffer[8] = (state23 + temp1) ^ r1 ^ state8;

        temp0 = r1 + (r2 ^ state13);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state24 = (state8 << 8) ^ MUL_ALPHA[state8 >>> 24] ^ state10 ^ (state19 >>> 8) ^ DIV_ALPHA[state19 & 0xff];

        buffer[9] = (state24 + temp0) ^ r1 ^ state9;

        temp1 = r1 + (r2 ^ state14);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state25 = (state9 << 8) ^ MUL_ALPHA[state9 >>> 24] ^ state11 ^ (state20 >>> 8) ^ DIV_ALPHA[state20 & 0xff];

        buffer[10] = (state25 + temp1) ^ r1 ^ state10;

        temp0 = r1 + (r2 ^ state15);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state26 = (state10 << 8) ^ MUL_ALPHA[state10 >>> 24] ^ state12 ^ (state21 >>> 8) ^ DIV_ALPHA[state21 & 0xff];

        buffer[11] = (state26 + temp0) ^ r1 ^ state11;

        temp1 = r1 + (r2 ^ state16);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state27 = (state11 << 8) ^ MUL_ALPHA[state11 >>> 24] ^ state13 ^ (state22 >>> 8) ^ DIV_ALPHA[state22 & 0xff];

        buffer[12] = (state27 + temp1) ^ r1 ^ state12;

        temp0 = r1 + (r2 ^ state17);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state28 = (state12 << 8) ^ MUL_ALPHA[state12 >>> 24] ^ state14 ^ (state23 >>> 8) ^ DIV_ALPHA[state23 & 0xff];

        buffer[13] = (state28 + temp0) ^ r1 ^ state13;

        temp1 = r1 + (r2 ^ state18);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state29 = (state13 << 8) ^ MUL_ALPHA[state13 >>> 24] ^ state15 ^ (state24 >>> 8) ^ DIV_ALPHA[state24 & 0xff];

        buffer[14] = (state29 + temp1) ^ r1 ^ state14;

        temp0 = r1 + (r2 ^ state19);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state30 = (state14 << 8) ^ MUL_ALPHA[state14 >>> 24] ^ state16 ^ (state25 >>> 8) ^ DIV_ALPHA[state25 & 0xff];

        buffer[15] = (state30 + temp0) ^ r1 ^ state15;

        temp1 = r1 + (r2 ^ state20);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state31 = (state15 << 8) ^ MUL_ALPHA[state15 >>> 24] ^ state17 ^ (state26 >>> 8) ^ DIV_ALPHA[state26 & 0xff];

        buffer[16] = (state31 + temp1) ^ r1 ^ state16;

        temp0 = r1 + (r2 ^ state21);
        r2 = s2(r1);
        r1 = s1(temp1);

        state0 = (state16 << 8) ^ MUL_ALPHA[state16 >>> 24] ^ state18 ^ (state27 >>> 8) ^ DIV_ALPHA[state27 & 0xff];

        buffer[17] = (state0 + temp0) ^ r1 ^ state17;

        temp1 = r1 + (r2 ^ state22);
        r2 = s2(r1);
        r1 = s1(temp0);

        state1 = (state17 << 8) ^ MUL_ALPHA[state17 >>> 24] ^ state19 ^ (state28 >>> 8) ^ DIV_ALPHA[state28 & 0xff];

        buffer[18] = (state1 + temp1) ^ r1 ^ state18;

        temp0 = r1 + (r2 ^ state23);
        r2 = s2(r1);
        r1 = s1(temp1);

        state2 = (state18 << 8) ^ MUL_ALPHA[state18 >>> 24] ^ state20 ^ (state29 >>> 8) ^ DIV_ALPHA[state29 & 0xff];

        buffer[19] = (state2 + temp0) ^ r1 ^ state19;

        temp1 = r1 + (r2 ^ state24);
        r2 = s2(r1);
        r1 = s1(temp0);

        state3 = (state19 << 8) ^ MUL_ALPHA[state19 >>> 24] ^ state21 ^ (state30 >>> 8) ^ DIV_ALPHA[state30 & 0xff];

        buffer[20] = (state3 + temp1) ^ r1 ^ state20;

        temp0 = r1 + (r2 ^ state25);
        r2 = s2(r1);
        r1 = s1(temp1);

        state4 = (state20 << 8) ^ MUL_ALPHA[state20 >>> 24] ^ state22 ^ (state31 >>> 8) ^ DIV_ALPHA[state31 & 0xff];

        buffer[21] = (state4 + temp0) ^ r1 ^ state21;

        temp1 = r1 + (r2 ^ state26);
        r2 = s2(r1);
        r1 = s1(temp0);

        state5 = (state21 << 8) ^ MUL_ALPHA[state21 >>> 24] ^ state23 ^ (state0 >>> 8) ^ DIV_ALPHA[state0 & 0xff];

        buffer[22] = (state5 + temp1) ^ r1 ^ state22;

        temp0 = r1 + (r2 ^ state27);
        r2 = s2(r1);
        r1 = s1(temp1);

        state6 = (state22 << 8) ^ MUL_ALPHA[state22 >>> 24] ^ state24 ^ (state1 >>> 8) ^ DIV_ALPHA[state1 & 0xff];

        buffer[23] = (state6 + temp0) ^ r1 ^ state23;

        temp1 = r1 + (r2 ^ state28);
        r2 = s2(r1);
        r1 = s1(temp0);

        state7 = (state23 << 8) ^ MUL_ALPHA[state23 >>> 24] ^ state25 ^ (state2 >>> 8) ^ DIV_ALPHA[state2 & 0xff];

        buffer[24] = (state7 + temp1) ^ r1 ^ state24;

        temp0 = r1 + (r2 ^ state29);
        r2 = s2(r1);
        r1 = s1(temp1);

        state8 = (state24 << 8) ^ MUL_ALPHA[state24 >>> 24] ^ state26 ^ (state3 >>> 8) ^ DIV_ALPHA[state3 & 0xff];

        buffer[25] = (state8 + temp0) ^ r1 ^ state25;

        temp1 = r1 + (r2 ^ state30);
        r2 = s2(r1);
        r1 = s1(temp0);

        state9 = (state25 << 8) ^ MUL_ALPHA[state25 >>> 24] ^ state27 ^ (state4 >>> 8) ^ DIV_ALPHA[state4 & 0xff];

        buffer[26] = (state9 + temp1) ^ r1 ^ state26;

        temp0 = r1 + (r2 ^ state31);
        r2 = s2(r1);
        r1 = s1(temp1);

        state10 = (state26 << 8) ^ MUL_ALPHA[state26 >>> 24] ^ state28 ^ (state5 >>> 8) ^ DIV_ALPHA[state5 & 0xff];

        buffer[27] = (state10 + temp0) ^ r1 ^ state27;

        temp1 = r1 + (r2 ^ state0);
        r2 = s2(r1);
        r1 = s1(temp0);

        state11 = (state27 << 8) ^ MUL_ALPHA[state27 >>> 24] ^ state29 ^ (state6 >>> 8) ^ DIV_ALPHA[state6 & 0xff];

        buffer[28] = (state11 + temp1) ^ r1 ^ state28;

        temp0 = r1 + (r2 ^ state1);
        r2 = s2(r1);
        r1 = s1(temp1);

        state12 = (state28 << 8) ^ MUL_ALPHA[state28 >>> 24] ^ state30 ^ (state7 >>> 8) ^ DIV_ALPHA[state7 & 0xff];

        buffer[29] = (state12 + temp0) ^ r1 ^ state29;

        temp1 = r1 + (r2 ^ state2);
        r2 = s2(r1);
        r1 = s1(temp0);

        state13 = (state29 << 8) ^ MUL_ALPHA[state29 >>> 24] ^ state31 ^ (state8 >>> 8) ^ DIV_ALPHA[state8 & 0xff];

        buffer[30] = (state13 + temp1) ^ r1 ^ state30;

        temp0 = r1 + (r2 ^ state3);
        r2 = s2(r1);
        r1 = s1(temp1);

        state14 = (state30 << 8) ^ MUL_ALPHA[state30 >>> 24] ^ state0 ^ (state9 >>> 8) ^ DIV_ALPHA[state9 & 0xff];

        buffer[31] = (state14 + temp0) ^ r1 ^ state31;

        temp1 = r1 + (r2 ^ state4);
        r2 = s2(r1);
        r1 = s1(temp0);
        r0 = temp1;

        state15 = (state31 << 8) ^ MUL_ALPHA[state31 >>> 24] ^ state1 ^ (state10 >>> 8) ^ DIV_ALPHA[state10 & 0xff];

        state[0] = state0;
        state[1] = state1;
        state[2] = state2;
        state[3] = state3;
        state[4] = state4;
        state[5] = state5;
        state[6] = state6;
        state[7] = state7;
        state[8] = state8;
        state[9] = state9;
        state[10] = state10;
        state[11] = state11;
        state[12] = state12;
        state[13] = state13;
        state[14] = state14;
        state[15] = state15;

    }

    private static int[] expand(byte[] key, byte[] iv, int[] register) {
        int k0 = Tools.load32BE(key, 0);
        int k1 = Tools.load32BE(key, 4);
        int k2 = Tools.load32BE(key, 8);
        int k3 = Tools.load32BE(key, 12);

        int state0 = ~k0, state1 = ~k1, state2 = ~k2, state3 = ~k3;
        int state4 = k0, state5 = k1, state6 = k2, state7 = k3;
        int state8 = state0, state9 = state1 ^ Tools.load32BE(iv, 12), state10 = state2 ^ Tools.load32BE(iv, 8), state11 = state3;
        int state12 = state4 ^ Tools.load32BE(iv, 4), state13 = state5, state14 = state6, state15 = state7 ^ Tools.load32BE(iv, 0);

        int r0=register[0], r1=register[1], r2=register[2];
        
        int temp0, temp1;

        int state16 = (state0 << 8) ^ MUL_ALPHA[state0 >>> 24] ^ state2 ^ (state11 >>> 8) ^ DIV_ALPHA[state11 & 0xff] ^ (state15 + r0) ^ r1;

        temp0 = r1 + (r2 ^ state5);
        r2 = s2(r1);
        r1 = s1(r0);

        int state17 = (state1 << 8) ^ MUL_ALPHA[state1 >>> 24] ^ state3 ^ (state12 >>> 8) ^ DIV_ALPHA[state12 & 0xff] ^ (state16 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state6);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state18 = (state2 << 8) ^ MUL_ALPHA[state2 >>> 24] ^ state4 ^ (state13 >>> 8) ^ DIV_ALPHA[state13 & 0xff] ^ (state17 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state7);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state19 = (state3 << 8) ^ MUL_ALPHA[state3 >>> 24] ^ state5 ^ (state14 >>> 8) ^ DIV_ALPHA[state14 & 0xff] ^ (state18 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state8);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state20 = (state4 << 8) ^ MUL_ALPHA[state4 >>> 24] ^ state6 ^ (state15 >>> 8) ^ DIV_ALPHA[state15 & 0xff] ^ (state19 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state9);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state21 = (state5 << 8) ^ MUL_ALPHA[state5 >>> 24] ^ state7 ^ (state16 >>> 8) ^ DIV_ALPHA[state16 & 0xff] ^ (state20 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state10);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state22 = (state6 << 8) ^ MUL_ALPHA[state6 >>> 24] ^ state8 ^ (state17 >>> 8) ^ DIV_ALPHA[state17 & 0xff] ^ (state21 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state11);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state23 = (state7 << 8) ^ MUL_ALPHA[state7 >>> 24] ^ state9 ^ (state18 >>> 8) ^ DIV_ALPHA[state18 & 0xff] ^ (state22 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state12);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state24 = (state8 << 8) ^ MUL_ALPHA[state8 >>> 24] ^ state10 ^ (state19 >>> 8) ^ DIV_ALPHA[state19 & 0xff] ^ (state23 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state13);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state25 = (state9 << 8) ^ MUL_ALPHA[state9 >>> 24] ^ state11 ^ (state20 >>> 8) ^ DIV_ALPHA[state20 & 0xff] ^ (state24 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state14);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state26 = (state10 << 8) ^ MUL_ALPHA[state10 >>> 24] ^ state12 ^ (state21 >>> 8) ^ DIV_ALPHA[state21 & 0xff] ^ (state25 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state15);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state27 = (state11 << 8) ^ MUL_ALPHA[state11 >>> 24] ^ state13 ^ (state22 >>> 8) ^ DIV_ALPHA[state22 & 0xff] ^ (state26 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state16);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state28 = (state12 << 8) ^ MUL_ALPHA[state12 >>> 24] ^ state14 ^ (state23 >>> 8) ^ DIV_ALPHA[state23 & 0xff] ^ (state27 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state17);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state29 = (state13 << 8) ^ MUL_ALPHA[state13 >>> 24] ^ state15 ^ (state24 >>> 8) ^ DIV_ALPHA[state24 & 0xff] ^ (state28 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state18);
        r2 = s2(r1);
        r1 = s1(temp0);

        int state30 = (state14 << 8) ^ MUL_ALPHA[state14 >>> 24] ^ state16 ^ (state25 >>> 8) ^ DIV_ALPHA[state25 & 0xff] ^ (state29 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state19);
        r2 = s2(r1);
        r1 = s1(temp1);

        int state31 = (state15 << 8) ^ MUL_ALPHA[state15 >>> 24] ^ state17 ^ (state26 >>> 8) ^ DIV_ALPHA[state26 & 0xff] ^ (state30 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state20);
        r2 = s2(r1);
        r1 = s1(temp0);

        state0 = (state16 << 8) ^ MUL_ALPHA[state16 >>> 24] ^ state18 ^ (state27 >>> 8) ^ DIV_ALPHA[state27 & 0xff] ^ (state31 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state21);
        r2 = s2(r1);
        r1 = s1(temp1);

        state1 = (state17 << 8) ^ MUL_ALPHA[state17 >>> 24] ^ state19 ^ (state28 >>> 8) ^ DIV_ALPHA[state28 & 0xff] ^ (state0 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state22);
        r2 = s2(r1);
        r1 = s1(temp0);

        state2 = (state18 << 8) ^ MUL_ALPHA[state18 >>> 24] ^ state20 ^ (state29 >>> 8) ^ DIV_ALPHA[state29 & 0xff] ^ (state1 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state23);
        r2 = s2(r1);
        r1 = s1(temp1);

        state3 = (state19 << 8) ^ MUL_ALPHA[state19 >>> 24] ^ state21 ^ (state30 >>> 8) ^ DIV_ALPHA[state30 & 0xff] ^ (state2 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state24);
        r2 = s2(r1);
        r1 = s1(temp0);

        state4 = (state20 << 8) ^ MUL_ALPHA[state20 >>> 24] ^ state22 ^ (state31 >>> 8) ^ DIV_ALPHA[state31 & 0xff] ^ (state3 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state25);
        r2 = s2(r1);
        r1 = s1(temp1);

        state5 = (state21 << 8) ^ MUL_ALPHA[state21 >>> 24] ^ state23 ^ (state0 >>> 8) ^ DIV_ALPHA[state0 & 0xff] ^ (state4 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state26);
        r2 = s2(r1);
        r1 = s1(temp0);

        state6 = (state22 << 8) ^ MUL_ALPHA[state22 >>> 24] ^ state24 ^ (state1 >>> 8) ^ DIV_ALPHA[state1 & 0xff] ^ (state5 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state27);
        r2 = s2(r1);
        r1 = s1(temp1);

        state7 = (state23 << 8) ^ MUL_ALPHA[state23 >>> 24] ^ state25 ^ (state2 >>> 8) ^ DIV_ALPHA[state2 & 0xff] ^ (state6 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state28);
        r2 = s2(r1);
        r1 = s1(temp0);

        state8 = (state24 << 8) ^ MUL_ALPHA[state24 >>> 24] ^ state26 ^ (state3 >>> 8) ^ DIV_ALPHA[state3 & 0xff] ^ (state7 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state29);
        r2 = s2(r1);
        r1 = s1(temp1);

        state9 = (state25 << 8) ^ MUL_ALPHA[state25 >>> 24] ^ state27 ^ (state4 >>> 8) ^ DIV_ALPHA[state4 & 0xff] ^ (state8 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state30);
        r2 = s2(r1);
        r1 = s1(temp0);

        state10 = (state26 << 8) ^ MUL_ALPHA[state26 >>> 24] ^ state28 ^ (state5 >>> 8) ^ DIV_ALPHA[state5 & 0xff] ^ (state9 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state31);
        r2 = s2(r1);
        r1 = s1(temp1);

        state11 = (state27 << 8) ^ MUL_ALPHA[state27 >>> 24] ^ state29 ^ (state6 >>> 8) ^ DIV_ALPHA[state6 & 0xff] ^ (state10 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state0);
        r2 = s2(r1);
        r1 = s1(temp0);

        state12 = (state28 << 8) ^ MUL_ALPHA[state28 >>> 24] ^ state30 ^ (state7 >>> 8) ^ DIV_ALPHA[state7 & 0xff] ^ (state11 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state1);
        r2 = s2(r1);
        r1 = s1(temp1);

        state13 = (state29 << 8) ^ MUL_ALPHA[state29 >>> 24] ^ state31 ^ (state8 >>> 8) ^ DIV_ALPHA[state8 & 0xff] ^ (state12 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state2);
        r2 = s2(r1);
        r1 = s1(temp0);

        state14 = (state30 << 8) ^ MUL_ALPHA[state30 >>> 24] ^ state0 ^ (state9 >>> 8) ^ DIV_ALPHA[state9 & 0xff] ^ (state13 + temp1) ^ r1;

        temp0 = r1 + (r2 ^ state3);
        r2 = s2(r1);
        r1 = s1(temp1);

        state15 = (state31 << 8) ^ MUL_ALPHA[state31 >>> 24] ^ state1 ^ (state10 >>> 8) ^ DIV_ALPHA[state10 & 0xff] ^ (state14 + temp0) ^ r1;

        temp1 = r1 + (r2 ^ state4);
        r2 = s2(r1);
        r1 = s1(temp0);

        register[0] = r1 + (r2 ^ state5);
        register[1] = s1(temp1);
        register[2] = s2(r1);

        int v = (state0 << 8) ^ MUL_ALPHA[state0 >>> 24] ^ state2 ^ (state11 >>> 8) ^ DIV_ALPHA[state11 & 0xff];

        return new int[]{
            state1, state2, state3, state4,
            state5, state6, state7, state8,
            state9, state10, state11, state12,
            state13, state14, state15, v
        };

    }

    @Override
    public EncryptEngine startEncryption(byte[] key, byte[] iv) {
        return new AbstractStreamEncrypter(128) {

            private final int[] register = new int[3], state = expand(key, iv, register), buffer = new int[32];

            @Override
            protected void encryptOneBlock(MemorySegment plaintext, long pOffset, MemorySegment ciphertext, long cOffset) {
                keystream(state, register, buffer);

                ciphertext.set(LAYOUT, cOffset + 0, plaintext.get(LAYOUT, pOffset + 0) ^ buffer[0]);
                ciphertext.set(LAYOUT, cOffset + 4, plaintext.get(LAYOUT, pOffset + 4) ^ buffer[1]);
                ciphertext.set(LAYOUT, cOffset + 8, plaintext.get(LAYOUT, pOffset + 8) ^ buffer[2]);
                ciphertext.set(LAYOUT, cOffset + 12, plaintext.get(LAYOUT, pOffset + 12) ^ buffer[3]);
                ciphertext.set(LAYOUT, cOffset + 16, plaintext.get(LAYOUT, pOffset + 16) ^ buffer[4]);
                ciphertext.set(LAYOUT, cOffset + 20, plaintext.get(LAYOUT, pOffset + 20) ^ buffer[5]);
                ciphertext.set(LAYOUT, cOffset + 24, plaintext.get(LAYOUT, pOffset + 24) ^ buffer[6]);
                ciphertext.set(LAYOUT, cOffset + 28, plaintext.get(LAYOUT, pOffset + 28) ^ buffer[7]);
                ciphertext.set(LAYOUT, cOffset + 32, plaintext.get(LAYOUT, pOffset + 32) ^ buffer[8]);
                ciphertext.set(LAYOUT, cOffset + 36, plaintext.get(LAYOUT, pOffset + 36) ^ buffer[9]);
                ciphertext.set(LAYOUT, cOffset + 40, plaintext.get(LAYOUT, pOffset + 40) ^ buffer[10]);
                ciphertext.set(LAYOUT, cOffset + 44, plaintext.get(LAYOUT, pOffset + 44) ^ buffer[11]);
                ciphertext.set(LAYOUT, cOffset + 48, plaintext.get(LAYOUT, pOffset + 48) ^ buffer[12]);
                ciphertext.set(LAYOUT, cOffset + 52, plaintext.get(LAYOUT, pOffset + 52) ^ buffer[13]);
                ciphertext.set(LAYOUT, cOffset + 56, plaintext.get(LAYOUT, pOffset + 56) ^ buffer[14]);
                ciphertext.set(LAYOUT, cOffset + 60, plaintext.get(LAYOUT, pOffset + 60) ^ buffer[15]);
                ciphertext.set(LAYOUT, cOffset + 64, plaintext.get(LAYOUT, pOffset + 64) ^ buffer[16]);
                ciphertext.set(LAYOUT, cOffset + 68, plaintext.get(LAYOUT, pOffset + 68) ^ buffer[17]);
                ciphertext.set(LAYOUT, cOffset + 72, plaintext.get(LAYOUT, pOffset + 72) ^ buffer[18]);
                ciphertext.set(LAYOUT, cOffset + 76, plaintext.get(LAYOUT, pOffset + 76) ^ buffer[19]);
                ciphertext.set(LAYOUT, cOffset + 80, plaintext.get(LAYOUT, pOffset + 80) ^ buffer[20]);
                ciphertext.set(LAYOUT, cOffset + 84, plaintext.get(LAYOUT, pOffset + 84) ^ buffer[21]);
                ciphertext.set(LAYOUT, cOffset + 88, plaintext.get(LAYOUT, pOffset + 88) ^ buffer[22]);
                ciphertext.set(LAYOUT, cOffset + 92, plaintext.get(LAYOUT, pOffset + 92) ^ buffer[23]);
                ciphertext.set(LAYOUT, cOffset + 96, plaintext.get(LAYOUT, pOffset + 96) ^ buffer[24]);
                ciphertext.set(LAYOUT, cOffset + 100, plaintext.get(LAYOUT, pOffset + 100) ^ buffer[25]);
                ciphertext.set(LAYOUT, cOffset + 104, plaintext.get(LAYOUT, pOffset + 104) ^ buffer[26]);
                ciphertext.set(LAYOUT, cOffset + 108, plaintext.get(LAYOUT, pOffset + 108) ^ buffer[27]);
                ciphertext.set(LAYOUT, cOffset + 112, plaintext.get(LAYOUT, pOffset + 112) ^ buffer[28]);
                ciphertext.set(LAYOUT, cOffset + 116, plaintext.get(LAYOUT, pOffset + 116) ^ buffer[29]);
                ciphertext.set(LAYOUT, cOffset + 120, plaintext.get(LAYOUT, pOffset + 120) ^ buffer[30]);
                ciphertext.set(LAYOUT, cOffset + 124, plaintext.get(LAYOUT, pOffset + 124) ^ buffer[31]);
            }

            @Override
            public Cipher getAlgorithm() {
                return SNOW_3G;
            }

        };
    }

    @Override
    public int keyLength() {
        return 16;
    }

    @Override
    public int ivLength() {
        return 16;
    }

}
