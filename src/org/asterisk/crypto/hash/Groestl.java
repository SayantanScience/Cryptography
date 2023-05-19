/*
 * Copyright (C) 2023 Sayantan Chakraborty
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.asterisk.crypto.hash;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import org.asterisk.crypto.helper.AbstractDigestEngine;
import org.asterisk.crypto.helper.Tools;
import org.asterisk.crypto.interfaces.Digest;

/**
 *
 * @author Sayantan Chakraborty
 */
public enum Groestl implements Digest {

    GROESTL_224 {
        @Override
        public Engine start() {
            return new Groest512Engine(new long[]{0, 0, 0, 0, 0, 0, 0, 0xe0}) {
                @Override
                protected void getDigest(byte[] dest, int offset) {
                    Tools.store32BE((int) state[4], dest, offset + 0);
                    Tools.store64BE(state[5], dest, offset + 4);
                    Tools.store64BE(state[6], dest, offset + 12);
                    Tools.store64BE(state[7], dest, offset + 20);
                }

                @Override
                public Digest getAlgorithm() {
                    return GROESTL_224;
                }
            };
        }

        @Override
        public int digestSize() {
            return 28;
        }

        @Override
        public int blockSize() {
            return 64;
        }

    }, GROESTL_256 {
        @Override
        public Engine start() {
            return new Groest512Engine(new long[]{0, 0, 0, 0, 0, 0, 0, 0x100}) {
                @Override
                protected void getDigest(byte[] dest, int offset) {
                    Tools.store64BE(state[4], dest, offset + 0);
                    Tools.store64BE(state[5], dest, offset + 8);
                    Tools.store64BE(state[6], dest, offset + 16);
                    Tools.store64BE(state[7], dest, offset + 24);
                }

                @Override
                public Digest getAlgorithm() {
                    return GROESTL_256;
                }
            };
        }

        @Override
        public int digestSize() {
            return 32;
        }

        @Override
        public int blockSize() {
            return 64;
        }

    }, GROESTL_384 {
        @Override
        public Engine start() {
            return new Groest512Engine(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0x180}) {
                @Override
                protected void getDigest(byte[] dest, int offset) {
                    Tools.store64BE(state[10], dest, offset + 0);
                    Tools.store64BE(state[11], dest, offset + 8);
                    Tools.store64BE(state[12], dest, offset + 16);
                    Tools.store64BE(state[13], dest, offset + 24);
                    Tools.store64BE(state[14], dest, offset + 32);
                    Tools.store64BE(state[15], dest, offset + 40);
                }

                @Override
                public Digest getAlgorithm() {
                    return GROESTL_384;
                }
            };
        }

        @Override
        public int digestSize() {
            return 48;
        }

        @Override
        public int blockSize() {
            return 128;
        }

    }, GROESTL_512 {
        @Override
        public Engine start() {
            return new Groest512Engine(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0x200}) {
                @Override
                protected void getDigest(byte[] dest, int offset) {
                    Tools.store64BE(state[8], dest, offset + 0);
                    Tools.store64BE(state[9], dest, offset + 8);
                    Tools.store64BE(state[10], dest, offset + 16);
                    Tools.store64BE(state[11], dest, offset + 24);
                    Tools.store64BE(state[12], dest, offset + 32);
                    Tools.store64BE(state[13], dest, offset + 40);
                    Tools.store64BE(state[14], dest, offset + 48);
                    Tools.store64BE(state[15], dest, offset + 56);
                }

                @Override
                public Digest getAlgorithm() {
                    return GROESTL_512;
                }
            };
        }

        @Override
        public int digestSize() {
            return 64;
        }

        @Override
        public int blockSize() {
            return 128;
        }

    };

    private static final ValueLayout.OfLong LAYOUT = Tools.BIG_ENDIAN_64_BIT;

    private static final long[] T0 = {
        0xc632f4a5f497a5c6L, 0xf86f978497eb84f8L, 0xee5eb099b0c799eeL, 0xf67a8c8d8cf78df6L,
        0xffe8170d17e50dffL, 0xd60adcbddcb7bdd6L, 0xde16c8b1c8a7b1deL, 0x916dfc54fc395491L,
        0x6090f050f0c05060L, 0x0207050305040302L, 0xce2ee0a9e087a9ceL, 0x56d1877d87ac7d56L,
        0xe7cc2b192bd519e7L, 0xb513a662a67162b5L, 0x4d7c31e6319ae64dL, 0xec59b59ab5c39aecL,
        0x8f40cf45cf05458fL, 0x1fa3bc9dbc3e9d1fL, 0x8949c040c0094089L, 0xfa68928792ef87faL,
        0xefd03f153fc515efL, 0xb29426eb267febb2L, 0x8ece40c94007c98eL, 0xfbe61d0b1ded0bfbL,
        0x416e2fec2f82ec41L, 0xb31aa967a97d67b3L, 0x5f431cfd1cbefd5fL, 0x456025ea258aea45L,
        0x23f9dabfda46bf23L, 0x535102f702a6f753L, 0xe445a196a1d396e4L, 0x9b76ed5bed2d5b9bL,
        0x75285dc25deac275L, 0xe1c5241c24d91ce1L, 0x3dd4e9aee97aae3dL, 0x4cf2be6abe986a4cL,
        0x6c82ee5aeed85a6cL, 0x7ebdc341c3fc417eL, 0xf5f3060206f102f5L, 0x8352d14fd11d4f83L,
        0x688ce45ce4d05c68L, 0x515607f407a2f451L, 0xd18d5c345cb934d1L, 0xf9e1180818e908f9L,
        0xe24cae93aedf93e2L, 0xab3e9573954d73abL, 0x6297f553f5c45362L, 0x2a6b413f41543f2aL,
        0x081c140c14100c08L, 0x9563f652f6315295L, 0x46e9af65af8c6546L, 0x9d7fe25ee2215e9dL,
        0x3048782878602830L, 0x37cff8a1f86ea137L, 0x0a1b110f11140f0aL, 0x2febc4b5c45eb52fL,
        0x0e151b091b1c090eL, 0x247e5a365a483624L, 0x1badb69bb6369b1bL, 0xdf98473d47a53ddfL,
        0xcda76a266a8126cdL, 0x4ef5bb69bb9c694eL, 0x7f334ccd4cfecd7fL, 0xea50ba9fbacf9feaL,
        0x123f2d1b2d241b12L, 0x1da4b99eb93a9e1dL, 0x58c49c749cb07458L, 0x3446722e72682e34L,
        0x3641772d776c2d36L, 0xdc11cdb2cda3b2dcL, 0xb49d29ee2973eeb4L, 0x5b4d16fb16b6fb5bL,
        0xa4a501f60153f6a4L, 0x76a1d74dd7ec4d76L, 0xb714a361a37561b7L, 0x7d3449ce49face7dL,
        0x52df8d7b8da47b52L, 0xdd9f423e42a13eddL, 0x5ecd937193bc715eL, 0x13b1a297a2269713L,
        0xa6a204f50457f5a6L, 0xb901b868b86968b9L, 0x0000000000000000L, 0xc1b5742c74992cc1L,
        0x40e0a060a0806040L, 0xe3c2211f21dd1fe3L, 0x793a43c843f2c879L, 0xb69a2ced2c77edb6L,
        0xd40dd9bed9b3bed4L, 0x8d47ca46ca01468dL, 0x671770d970ced967L, 0x72afdd4bdde44b72L,
        0x94ed79de7933de94L, 0x98ff67d4672bd498L, 0xb09323e8237be8b0L, 0x855bde4ade114a85L,
        0xbb06bd6bbd6d6bbbL, 0xc5bb7e2a7e912ac5L, 0x4f7b34e5349ee54fL, 0xedd73a163ac116edL,
        0x86d254c55417c586L, 0x9af862d7622fd79aL, 0x6699ff55ffcc5566L, 0x11b6a794a7229411L,
        0x8ac04acf4a0fcf8aL, 0xe9d9301030c910e9L, 0x040e0a060a080604L, 0xfe66988198e781feL,
        0xa0ab0bf00b5bf0a0L, 0x78b4cc44ccf04478L, 0x25f0d5bad54aba25L, 0x4b753ee33e96e34bL,
        0xa2ac0ef30e5ff3a2L, 0x5d4419fe19bafe5dL, 0x80db5bc05b1bc080L, 0x0580858a850a8a05L,
        0x3fd3ecadec7ead3fL, 0x21fedfbcdf42bc21L, 0x70a8d848d8e04870L, 0xf1fd0c040cf904f1L,
        0x63197adf7ac6df63L, 0x772f58c158eec177L, 0xaf309f759f4575afL, 0x42e7a563a5846342L,
        0x2070503050403020L, 0xe5cb2e1a2ed11ae5L, 0xfdef120e12e10efdL, 0xbf08b76db7656dbfL,
        0x8155d44cd4194c81L, 0x18243c143c301418L, 0x26795f355f4c3526L, 0xc3b2712f719d2fc3L,
        0xbe8638e13867e1beL, 0x35c8fda2fd6aa235L, 0x88c74fcc4f0bcc88L, 0x2e654b394b5c392eL,
        0x936af957f93d5793L, 0x55580df20daaf255L, 0xfc619d829de382fcL, 0x7ab3c947c9f4477aL,
        0xc827efacef8bacc8L, 0xba8832e7326fe7baL, 0x324f7d2b7d642b32L, 0xe642a495a4d795e6L,
        0xc03bfba0fb9ba0c0L, 0x19aab398b3329819L, 0x9ef668d16827d19eL, 0xa322817f815d7fa3L,
        0x44eeaa66aa886644L, 0x54d6827e82a87e54L, 0x3bdde6abe676ab3bL, 0x0b959e839e16830bL,
        0x8cc945ca4503ca8cL, 0xc7bc7b297b9529c7L, 0x6b056ed36ed6d36bL, 0x286c443c44503c28L,
        0xa72c8b798b5579a7L, 0xbc813de23d63e2bcL, 0x1631271d272c1d16L, 0xad379a769a4176adL,
        0xdb964d3b4dad3bdbL, 0x649efa56fac85664L, 0x74a6d24ed2e84e74L, 0x1436221e22281e14L,
        0x92e476db763fdb92L, 0x0c121e0a1e180a0cL, 0x48fcb46cb4906c48L, 0xb88f37e4376be4b8L,
        0x9f78e75de7255d9fL, 0xbd0fb26eb2616ebdL, 0x43692aef2a86ef43L, 0xc435f1a6f193a6c4L,
        0x39dae3a8e372a839L, 0x31c6f7a4f762a431L, 0xd38a593759bd37d3L, 0xf274868b86ff8bf2L,
        0xd583563256b132d5L, 0x8b4ec543c50d438bL, 0x6e85eb59ebdc596eL, 0xda18c2b7c2afb7daL,
        0x018e8f8c8f028c01L, 0xb11dac64ac7964b1L, 0x9cf16dd26d23d29cL, 0x49723be03b92e049L,
        0xd81fc7b4c7abb4d8L, 0xacb915fa1543faacL, 0xf3fa090709fd07f3L, 0xcfa06f256f8525cfL,
        0xca20eaafea8fafcaL, 0xf47d898e89f38ef4L, 0x476720e9208ee947L, 0x1038281828201810L,
        0x6f0b64d564ded56fL, 0xf073838883fb88f0L, 0x4afbb16fb1946f4aL, 0x5cca967296b8725cL,
        0x38546c246c702438L, 0x575f08f108aef157L, 0x732152c752e6c773L, 0x9764f351f3355197L,
        0xcbae6523658d23cbL, 0xa125847c84597ca1L, 0xe857bf9cbfcb9ce8L, 0x3e5d6321637c213eL,
        0x96ea7cdd7c37dd96L, 0x611e7fdc7fc2dc61L, 0x0d9c9186911a860dL, 0x0f9b9485941e850fL,
        0xe04bab90abdb90e0L, 0x7cbac642c6f8427cL, 0x712657c457e2c471L, 0xcc29e5aae583aaccL,
        0x90e373d8733bd890L, 0x06090f050f0c0506L, 0xf7f4030103f501f7L, 0x1c2a36123638121cL,
        0xc23cfea3fe9fa3c2L, 0x6a8be15fe1d45f6aL, 0xaebe10f91047f9aeL, 0x69026bd06bd2d069L,
        0x17bfa891a82e9117L, 0x9971e858e8295899L, 0x3a5369276974273aL, 0x27f7d0b9d04eb927L,
        0xd991483848a938d9L, 0xebde351335cd13ebL, 0x2be5ceb3ce56b32bL, 0x2277553355443322L,
        0xd204d6bbd6bfbbd2L, 0xa9399070904970a9L, 0x07878089800e8907L, 0x33c1f2a7f266a733L,
        0x2decc1b6c15ab62dL, 0x3c5a66226678223cL, 0x15b8ad92ad2a9215L, 0xc9a96020608920c9L,
        0x875cdb49db154987L, 0xaab01aff1a4fffaaL, 0x50d8887888a07850L, 0xa52b8e7a8e517aa5L,
        0x03898a8f8a068f03L, 0x594a13f813b2f859L, 0x09929b809b128009L, 0x1a2339173934171aL,
        0x651075da75cada65L, 0xd784533153b531d7L, 0x84d551c65113c684L, 0xd003d3b8d3bbb8d0L,
        0x82dc5ec35e1fc382L, 0x29e2cbb0cb52b029L, 0x5ac3997799b4775aL, 0x1e2d3311333c111eL,
        0x7b3d46cb46f6cb7bL, 0xa8b71ffc1f4bfca8L, 0x6d0c61d661dad66dL, 0x2c624e3a4e583a2cL
    };

    private static final long[] T1 = {
        0xc6c632f4a5f497a5L, 0xf8f86f978497eb84L, 0xeeee5eb099b0c799L, 0xf6f67a8c8d8cf78dL,
        0xffffe8170d17e50dL, 0xd6d60adcbddcb7bdL, 0xdede16c8b1c8a7b1L, 0x91916dfc54fc3954L,
        0x606090f050f0c050L, 0x0202070503050403L, 0xcece2ee0a9e087a9L, 0x5656d1877d87ac7dL,
        0xe7e7cc2b192bd519L, 0xb5b513a662a67162L, 0x4d4d7c31e6319ae6L, 0xecec59b59ab5c39aL,
        0x8f8f40cf45cf0545L, 0x1f1fa3bc9dbc3e9dL, 0x898949c040c00940L, 0xfafa68928792ef87L,
        0xefefd03f153fc515L, 0xb2b29426eb267febL, 0x8e8ece40c94007c9L, 0xfbfbe61d0b1ded0bL,
        0x41416e2fec2f82ecL, 0xb3b31aa967a97d67L, 0x5f5f431cfd1cbefdL, 0x45456025ea258aeaL,
        0x2323f9dabfda46bfL, 0x53535102f702a6f7L, 0xe4e445a196a1d396L, 0x9b9b76ed5bed2d5bL,
        0x7575285dc25deac2L, 0xe1e1c5241c24d91cL, 0x3d3dd4e9aee97aaeL, 0x4c4cf2be6abe986aL,
        0x6c6c82ee5aeed85aL, 0x7e7ebdc341c3fc41L, 0xf5f5f3060206f102L, 0x838352d14fd11d4fL,
        0x68688ce45ce4d05cL, 0x51515607f407a2f4L, 0xd1d18d5c345cb934L, 0xf9f9e1180818e908L,
        0xe2e24cae93aedf93L, 0xabab3e9573954d73L, 0x626297f553f5c453L, 0x2a2a6b413f41543fL,
        0x08081c140c14100cL, 0x959563f652f63152L, 0x4646e9af65af8c65L, 0x9d9d7fe25ee2215eL,
        0x3030487828786028L, 0x3737cff8a1f86ea1L, 0x0a0a1b110f11140fL, 0x2f2febc4b5c45eb5L,
        0x0e0e151b091b1c09L, 0x24247e5a365a4836L, 0x1b1badb69bb6369bL, 0xdfdf98473d47a53dL,
        0xcdcda76a266a8126L, 0x4e4ef5bb69bb9c69L, 0x7f7f334ccd4cfecdL, 0xeaea50ba9fbacf9fL,
        0x12123f2d1b2d241bL, 0x1d1da4b99eb93a9eL, 0x5858c49c749cb074L, 0x343446722e72682eL,
        0x363641772d776c2dL, 0xdcdc11cdb2cda3b2L, 0xb4b49d29ee2973eeL, 0x5b5b4d16fb16b6fbL,
        0xa4a4a501f60153f6L, 0x7676a1d74dd7ec4dL, 0xb7b714a361a37561L, 0x7d7d3449ce49faceL,
        0x5252df8d7b8da47bL, 0xdddd9f423e42a13eL, 0x5e5ecd937193bc71L, 0x1313b1a297a22697L,
        0xa6a6a204f50457f5L, 0xb9b901b868b86968L, 0x0000000000000000L, 0xc1c1b5742c74992cL,
        0x4040e0a060a08060L, 0xe3e3c2211f21dd1fL, 0x79793a43c843f2c8L, 0xb6b69a2ced2c77edL,
        0xd4d40dd9bed9b3beL, 0x8d8d47ca46ca0146L, 0x67671770d970ced9L, 0x7272afdd4bdde44bL,
        0x9494ed79de7933deL, 0x9898ff67d4672bd4L, 0xb0b09323e8237be8L, 0x85855bde4ade114aL,
        0xbbbb06bd6bbd6d6bL, 0xc5c5bb7e2a7e912aL, 0x4f4f7b34e5349ee5L, 0xededd73a163ac116L,
        0x8686d254c55417c5L, 0x9a9af862d7622fd7L, 0x666699ff55ffcc55L, 0x1111b6a794a72294L,
        0x8a8ac04acf4a0fcfL, 0xe9e9d9301030c910L, 0x04040e0a060a0806L, 0xfefe66988198e781L,
        0xa0a0ab0bf00b5bf0L, 0x7878b4cc44ccf044L, 0x2525f0d5bad54abaL, 0x4b4b753ee33e96e3L,
        0xa2a2ac0ef30e5ff3L, 0x5d5d4419fe19bafeL, 0x8080db5bc05b1bc0L, 0x050580858a850a8aL,
        0x3f3fd3ecadec7eadL, 0x2121fedfbcdf42bcL, 0x7070a8d848d8e048L, 0xf1f1fd0c040cf904L,
        0x6363197adf7ac6dfL, 0x77772f58c158eec1L, 0xafaf309f759f4575L, 0x4242e7a563a58463L,
        0x2020705030504030L, 0xe5e5cb2e1a2ed11aL, 0xfdfdef120e12e10eL, 0xbfbf08b76db7656dL,
        0x818155d44cd4194cL, 0x1818243c143c3014L, 0x2626795f355f4c35L, 0xc3c3b2712f719d2fL,
        0xbebe8638e13867e1L, 0x3535c8fda2fd6aa2L, 0x8888c74fcc4f0bccL, 0x2e2e654b394b5c39L,
        0x93936af957f93d57L, 0x5555580df20daaf2L, 0xfcfc619d829de382L, 0x7a7ab3c947c9f447L,
        0xc8c827efacef8bacL, 0xbaba8832e7326fe7L, 0x32324f7d2b7d642bL, 0xe6e642a495a4d795L,
        0xc0c03bfba0fb9ba0L, 0x1919aab398b33298L, 0x9e9ef668d16827d1L, 0xa3a322817f815d7fL,
        0x4444eeaa66aa8866L, 0x5454d6827e82a87eL, 0x3b3bdde6abe676abL, 0x0b0b959e839e1683L,
        0x8c8cc945ca4503caL, 0xc7c7bc7b297b9529L, 0x6b6b056ed36ed6d3L, 0x28286c443c44503cL,
        0xa7a72c8b798b5579L, 0xbcbc813de23d63e2L, 0x161631271d272c1dL, 0xadad379a769a4176L,
        0xdbdb964d3b4dad3bL, 0x64649efa56fac856L, 0x7474a6d24ed2e84eL, 0x141436221e22281eL,
        0x9292e476db763fdbL, 0x0c0c121e0a1e180aL, 0x4848fcb46cb4906cL, 0xb8b88f37e4376be4L,
        0x9f9f78e75de7255dL, 0xbdbd0fb26eb2616eL, 0x4343692aef2a86efL, 0xc4c435f1a6f193a6L,
        0x3939dae3a8e372a8L, 0x3131c6f7a4f762a4L, 0xd3d38a593759bd37L, 0xf2f274868b86ff8bL,
        0xd5d583563256b132L, 0x8b8b4ec543c50d43L, 0x6e6e85eb59ebdc59L, 0xdada18c2b7c2afb7L,
        0x01018e8f8c8f028cL, 0xb1b11dac64ac7964L, 0x9c9cf16dd26d23d2L, 0x4949723be03b92e0L,
        0xd8d81fc7b4c7abb4L, 0xacacb915fa1543faL, 0xf3f3fa090709fd07L, 0xcfcfa06f256f8525L,
        0xcaca20eaafea8fafL, 0xf4f47d898e89f38eL, 0x47476720e9208ee9L, 0x1010382818282018L,
        0x6f6f0b64d564ded5L, 0xf0f073838883fb88L, 0x4a4afbb16fb1946fL, 0x5c5cca967296b872L,
        0x3838546c246c7024L, 0x57575f08f108aef1L, 0x73732152c752e6c7L, 0x979764f351f33551L,
        0xcbcbae6523658d23L, 0xa1a125847c84597cL, 0xe8e857bf9cbfcb9cL, 0x3e3e5d6321637c21L,
        0x9696ea7cdd7c37ddL, 0x61611e7fdc7fc2dcL, 0x0d0d9c9186911a86L, 0x0f0f9b9485941e85L,
        0xe0e04bab90abdb90L, 0x7c7cbac642c6f842L, 0x71712657c457e2c4L, 0xcccc29e5aae583aaL,
        0x9090e373d8733bd8L, 0x0606090f050f0c05L, 0xf7f7f4030103f501L, 0x1c1c2a3612363812L,
        0xc2c23cfea3fe9fa3L, 0x6a6a8be15fe1d45fL, 0xaeaebe10f91047f9L, 0x6969026bd06bd2d0L,
        0x1717bfa891a82e91L, 0x999971e858e82958L, 0x3a3a536927697427L, 0x2727f7d0b9d04eb9L,
        0xd9d991483848a938L, 0xebebde351335cd13L, 0x2b2be5ceb3ce56b3L, 0x2222775533554433L,
        0xd2d204d6bbd6bfbbL, 0xa9a9399070904970L, 0x0707878089800e89L, 0x3333c1f2a7f266a7L,
        0x2d2decc1b6c15ab6L, 0x3c3c5a6622667822L, 0x1515b8ad92ad2a92L, 0xc9c9a96020608920L,
        0x87875cdb49db1549L, 0xaaaab01aff1a4fffL, 0x5050d8887888a078L, 0xa5a52b8e7a8e517aL,
        0x0303898a8f8a068fL, 0x59594a13f813b2f8L, 0x0909929b809b1280L, 0x1a1a233917393417L,
        0x65651075da75cadaL, 0xd7d784533153b531L, 0x8484d551c65113c6L, 0xd0d003d3b8d3bbb8L,
        0x8282dc5ec35e1fc3L, 0x2929e2cbb0cb52b0L, 0x5a5ac3997799b477L, 0x1e1e2d3311333c11L,
        0x7b7b3d46cb46f6cbL, 0xa8a8b71ffc1f4bfcL, 0x6d6d0c61d661dad6L, 0x2c2c624e3a4e583aL
    };

    private static final long[] T2 = {
        0xa5c6c632f4a5f497L, 0x84f8f86f978497ebL, 0x99eeee5eb099b0c7L, 0x8df6f67a8c8d8cf7L,
        0x0dffffe8170d17e5L, 0xbdd6d60adcbddcb7L, 0xb1dede16c8b1c8a7L, 0x5491916dfc54fc39L,
        0x50606090f050f0c0L, 0x0302020705030504L, 0xa9cece2ee0a9e087L, 0x7d5656d1877d87acL,
        0x19e7e7cc2b192bd5L, 0x62b5b513a662a671L, 0xe64d4d7c31e6319aL, 0x9aecec59b59ab5c3L,
        0x458f8f40cf45cf05L, 0x9d1f1fa3bc9dbc3eL, 0x40898949c040c009L, 0x87fafa68928792efL,
        0x15efefd03f153fc5L, 0xebb2b29426eb267fL, 0xc98e8ece40c94007L, 0x0bfbfbe61d0b1dedL,
        0xec41416e2fec2f82L, 0x67b3b31aa967a97dL, 0xfd5f5f431cfd1cbeL, 0xea45456025ea258aL,
        0xbf2323f9dabfda46L, 0xf753535102f702a6L, 0x96e4e445a196a1d3L, 0x5b9b9b76ed5bed2dL,
        0xc27575285dc25deaL, 0x1ce1e1c5241c24d9L, 0xae3d3dd4e9aee97aL, 0x6a4c4cf2be6abe98L,
        0x5a6c6c82ee5aeed8L, 0x417e7ebdc341c3fcL, 0x02f5f5f3060206f1L, 0x4f838352d14fd11dL,
        0x5c68688ce45ce4d0L, 0xf451515607f407a2L, 0x34d1d18d5c345cb9L, 0x08f9f9e1180818e9L,
        0x93e2e24cae93aedfL, 0x73abab3e9573954dL, 0x53626297f553f5c4L, 0x3f2a2a6b413f4154L,
        0x0c08081c140c1410L, 0x52959563f652f631L, 0x654646e9af65af8cL, 0x5e9d9d7fe25ee221L,
        0x2830304878287860L, 0xa13737cff8a1f86eL, 0x0f0a0a1b110f1114L, 0xb52f2febc4b5c45eL,
        0x090e0e151b091b1cL, 0x3624247e5a365a48L, 0x9b1b1badb69bb636L, 0x3ddfdf98473d47a5L,
        0x26cdcda76a266a81L, 0x694e4ef5bb69bb9cL, 0xcd7f7f334ccd4cfeL, 0x9feaea50ba9fbacfL,
        0x1b12123f2d1b2d24L, 0x9e1d1da4b99eb93aL, 0x745858c49c749cb0L, 0x2e343446722e7268L,
        0x2d363641772d776cL, 0xb2dcdc11cdb2cda3L, 0xeeb4b49d29ee2973L, 0xfb5b5b4d16fb16b6L,
        0xf6a4a4a501f60153L, 0x4d7676a1d74dd7ecL, 0x61b7b714a361a375L, 0xce7d7d3449ce49faL,
        0x7b5252df8d7b8da4L, 0x3edddd9f423e42a1L, 0x715e5ecd937193bcL, 0x971313b1a297a226L,
        0xf5a6a6a204f50457L, 0x68b9b901b868b869L, 0x0000000000000000L, 0x2cc1c1b5742c7499L,
        0x604040e0a060a080L, 0x1fe3e3c2211f21ddL, 0xc879793a43c843f2L, 0xedb6b69a2ced2c77L,
        0xbed4d40dd9bed9b3L, 0x468d8d47ca46ca01L, 0xd967671770d970ceL, 0x4b7272afdd4bdde4L,
        0xde9494ed79de7933L, 0xd49898ff67d4672bL, 0xe8b0b09323e8237bL, 0x4a85855bde4ade11L,
        0x6bbbbb06bd6bbd6dL, 0x2ac5c5bb7e2a7e91L, 0xe54f4f7b34e5349eL, 0x16ededd73a163ac1L,
        0xc58686d254c55417L, 0xd79a9af862d7622fL, 0x55666699ff55ffccL, 0x941111b6a794a722L,
        0xcf8a8ac04acf4a0fL, 0x10e9e9d9301030c9L, 0x0604040e0a060a08L, 0x81fefe66988198e7L,
        0xf0a0a0ab0bf00b5bL, 0x447878b4cc44ccf0L, 0xba2525f0d5bad54aL, 0xe34b4b753ee33e96L,
        0xf3a2a2ac0ef30e5fL, 0xfe5d5d4419fe19baL, 0xc08080db5bc05b1bL, 0x8a050580858a850aL,
        0xad3f3fd3ecadec7eL, 0xbc2121fedfbcdf42L, 0x487070a8d848d8e0L, 0x04f1f1fd0c040cf9L,
        0xdf6363197adf7ac6L, 0xc177772f58c158eeL, 0x75afaf309f759f45L, 0x634242e7a563a584L,
        0x3020207050305040L, 0x1ae5e5cb2e1a2ed1L, 0x0efdfdef120e12e1L, 0x6dbfbf08b76db765L,
        0x4c818155d44cd419L, 0x141818243c143c30L, 0x352626795f355f4cL, 0x2fc3c3b2712f719dL,
        0xe1bebe8638e13867L, 0xa23535c8fda2fd6aL, 0xcc8888c74fcc4f0bL, 0x392e2e654b394b5cL,
        0x5793936af957f93dL, 0xf25555580df20daaL, 0x82fcfc619d829de3L, 0x477a7ab3c947c9f4L,
        0xacc8c827efacef8bL, 0xe7baba8832e7326fL, 0x2b32324f7d2b7d64L, 0x95e6e642a495a4d7L,
        0xa0c0c03bfba0fb9bL, 0x981919aab398b332L, 0xd19e9ef668d16827L, 0x7fa3a322817f815dL,
        0x664444eeaa66aa88L, 0x7e5454d6827e82a8L, 0xab3b3bdde6abe676L, 0x830b0b959e839e16L,
        0xca8c8cc945ca4503L, 0x29c7c7bc7b297b95L, 0xd36b6b056ed36ed6L, 0x3c28286c443c4450L,
        0x79a7a72c8b798b55L, 0xe2bcbc813de23d63L, 0x1d161631271d272cL, 0x76adad379a769a41L,
        0x3bdbdb964d3b4dadL, 0x5664649efa56fac8L, 0x4e7474a6d24ed2e8L, 0x1e141436221e2228L,
        0xdb9292e476db763fL, 0x0a0c0c121e0a1e18L, 0x6c4848fcb46cb490L, 0xe4b8b88f37e4376bL,
        0x5d9f9f78e75de725L, 0x6ebdbd0fb26eb261L, 0xef4343692aef2a86L, 0xa6c4c435f1a6f193L,
        0xa83939dae3a8e372L, 0xa43131c6f7a4f762L, 0x37d3d38a593759bdL, 0x8bf2f274868b86ffL,
        0x32d5d583563256b1L, 0x438b8b4ec543c50dL, 0x596e6e85eb59ebdcL, 0xb7dada18c2b7c2afL,
        0x8c01018e8f8c8f02L, 0x64b1b11dac64ac79L, 0xd29c9cf16dd26d23L, 0xe04949723be03b92L,
        0xb4d8d81fc7b4c7abL, 0xfaacacb915fa1543L, 0x07f3f3fa090709fdL, 0x25cfcfa06f256f85L,
        0xafcaca20eaafea8fL, 0x8ef4f47d898e89f3L, 0xe947476720e9208eL, 0x1810103828182820L,
        0xd56f6f0b64d564deL, 0x88f0f073838883fbL, 0x6f4a4afbb16fb194L, 0x725c5cca967296b8L,
        0x243838546c246c70L, 0xf157575f08f108aeL, 0xc773732152c752e6L, 0x51979764f351f335L,
        0x23cbcbae6523658dL, 0x7ca1a125847c8459L, 0x9ce8e857bf9cbfcbL, 0x213e3e5d6321637cL,
        0xdd9696ea7cdd7c37L, 0xdc61611e7fdc7fc2L, 0x860d0d9c9186911aL, 0x850f0f9b9485941eL,
        0x90e0e04bab90abdbL, 0x427c7cbac642c6f8L, 0xc471712657c457e2L, 0xaacccc29e5aae583L,
        0xd89090e373d8733bL, 0x050606090f050f0cL, 0x01f7f7f4030103f5L, 0x121c1c2a36123638L,
        0xa3c2c23cfea3fe9fL, 0x5f6a6a8be15fe1d4L, 0xf9aeaebe10f91047L, 0xd06969026bd06bd2L,
        0x911717bfa891a82eL, 0x58999971e858e829L, 0x273a3a5369276974L, 0xb92727f7d0b9d04eL,
        0x38d9d991483848a9L, 0x13ebebde351335cdL, 0xb32b2be5ceb3ce56L, 0x3322227755335544L,
        0xbbd2d204d6bbd6bfL, 0x70a9a93990709049L, 0x890707878089800eL, 0xa73333c1f2a7f266L,
        0xb62d2decc1b6c15aL, 0x223c3c5a66226678L, 0x921515b8ad92ad2aL, 0x20c9c9a960206089L,
        0x4987875cdb49db15L, 0xffaaaab01aff1a4fL, 0x785050d8887888a0L, 0x7aa5a52b8e7a8e51L,
        0x8f0303898a8f8a06L, 0xf859594a13f813b2L, 0x800909929b809b12L, 0x171a1a2339173934L,
        0xda65651075da75caL, 0x31d7d784533153b5L, 0xc68484d551c65113L, 0xb8d0d003d3b8d3bbL,
        0xc38282dc5ec35e1fL, 0xb02929e2cbb0cb52L, 0x775a5ac3997799b4L, 0x111e1e2d3311333cL,
        0xcb7b7b3d46cb46f6L, 0xfca8a8b71ffc1f4bL, 0xd66d6d0c61d661daL, 0x3a2c2c624e3a4e58L
    };

    private static final long[] T3 = {
        0x97a5c6c632f4a5f4L, 0xeb84f8f86f978497L, 0xc799eeee5eb099b0L, 0xf78df6f67a8c8d8cL,
        0xe50dffffe8170d17L, 0xb7bdd6d60adcbddcL, 0xa7b1dede16c8b1c8L, 0x395491916dfc54fcL,
        0xc050606090f050f0L, 0x0403020207050305L, 0x87a9cece2ee0a9e0L, 0xac7d5656d1877d87L,
        0xd519e7e7cc2b192bL, 0x7162b5b513a662a6L, 0x9ae64d4d7c31e631L, 0xc39aecec59b59ab5L,
        0x05458f8f40cf45cfL, 0x3e9d1f1fa3bc9dbcL, 0x0940898949c040c0L, 0xef87fafa68928792L,
        0xc515efefd03f153fL, 0x7febb2b29426eb26L, 0x07c98e8ece40c940L, 0xed0bfbfbe61d0b1dL,
        0x82ec41416e2fec2fL, 0x7d67b3b31aa967a9L, 0xbefd5f5f431cfd1cL, 0x8aea45456025ea25L,
        0x46bf2323f9dabfdaL, 0xa6f753535102f702L, 0xd396e4e445a196a1L, 0x2d5b9b9b76ed5bedL,
        0xeac27575285dc25dL, 0xd91ce1e1c5241c24L, 0x7aae3d3dd4e9aee9L, 0x986a4c4cf2be6abeL,
        0xd85a6c6c82ee5aeeL, 0xfc417e7ebdc341c3L, 0xf102f5f5f3060206L, 0x1d4f838352d14fd1L,
        0xd05c68688ce45ce4L, 0xa2f451515607f407L, 0xb934d1d18d5c345cL, 0xe908f9f9e1180818L,
        0xdf93e2e24cae93aeL, 0x4d73abab3e957395L, 0xc453626297f553f5L, 0x543f2a2a6b413f41L,
        0x100c08081c140c14L, 0x3152959563f652f6L, 0x8c654646e9af65afL, 0x215e9d9d7fe25ee2L,
        0x6028303048782878L, 0x6ea13737cff8a1f8L, 0x140f0a0a1b110f11L, 0x5eb52f2febc4b5c4L,
        0x1c090e0e151b091bL, 0x483624247e5a365aL, 0x369b1b1badb69bb6L, 0xa53ddfdf98473d47L,
        0x8126cdcda76a266aL, 0x9c694e4ef5bb69bbL, 0xfecd7f7f334ccd4cL, 0xcf9feaea50ba9fbaL,
        0x241b12123f2d1b2dL, 0x3a9e1d1da4b99eb9L, 0xb0745858c49c749cL, 0x682e343446722e72L,
        0x6c2d363641772d77L, 0xa3b2dcdc11cdb2cdL, 0x73eeb4b49d29ee29L, 0xb6fb5b5b4d16fb16L,
        0x53f6a4a4a501f601L, 0xec4d7676a1d74dd7L, 0x7561b7b714a361a3L, 0xface7d7d3449ce49L,
        0xa47b5252df8d7b8dL, 0xa13edddd9f423e42L, 0xbc715e5ecd937193L, 0x26971313b1a297a2L,
        0x57f5a6a6a204f504L, 0x6968b9b901b868b8L, 0x0000000000000000L, 0x992cc1c1b5742c74L,
        0x80604040e0a060a0L, 0xdd1fe3e3c2211f21L, 0xf2c879793a43c843L, 0x77edb6b69a2ced2cL,
        0xb3bed4d40dd9bed9L, 0x01468d8d47ca46caL, 0xced967671770d970L, 0xe44b7272afdd4bddL,
        0x33de9494ed79de79L, 0x2bd49898ff67d467L, 0x7be8b0b09323e823L, 0x114a85855bde4adeL,
        0x6d6bbbbb06bd6bbdL, 0x912ac5c5bb7e2a7eL, 0x9ee54f4f7b34e534L, 0xc116ededd73a163aL,
        0x17c58686d254c554L, 0x2fd79a9af862d762L, 0xcc55666699ff55ffL, 0x22941111b6a794a7L,
        0x0fcf8a8ac04acf4aL, 0xc910e9e9d9301030L, 0x080604040e0a060aL, 0xe781fefe66988198L,
        0x5bf0a0a0ab0bf00bL, 0xf0447878b4cc44ccL, 0x4aba2525f0d5bad5L, 0x96e34b4b753ee33eL,
        0x5ff3a2a2ac0ef30eL, 0xbafe5d5d4419fe19L, 0x1bc08080db5bc05bL, 0x0a8a050580858a85L,
        0x7ead3f3fd3ecadecL, 0x42bc2121fedfbcdfL, 0xe0487070a8d848d8L, 0xf904f1f1fd0c040cL,
        0xc6df6363197adf7aL, 0xeec177772f58c158L, 0x4575afaf309f759fL, 0x84634242e7a563a5L,
        0x4030202070503050L, 0xd11ae5e5cb2e1a2eL, 0xe10efdfdef120e12L, 0x656dbfbf08b76db7L,
        0x194c818155d44cd4L, 0x30141818243c143cL, 0x4c352626795f355fL, 0x9d2fc3c3b2712f71L,
        0x67e1bebe8638e138L, 0x6aa23535c8fda2fdL, 0x0bcc8888c74fcc4fL, 0x5c392e2e654b394bL,
        0x3d5793936af957f9L, 0xaaf25555580df20dL, 0xe382fcfc619d829dL, 0xf4477a7ab3c947c9L,
        0x8bacc8c827efacefL, 0x6fe7baba8832e732L, 0x642b32324f7d2b7dL, 0xd795e6e642a495a4L,
        0x9ba0c0c03bfba0fbL, 0x32981919aab398b3L, 0x27d19e9ef668d168L, 0x5d7fa3a322817f81L,
        0x88664444eeaa66aaL, 0xa87e5454d6827e82L, 0x76ab3b3bdde6abe6L, 0x16830b0b959e839eL,
        0x03ca8c8cc945ca45L, 0x9529c7c7bc7b297bL, 0xd6d36b6b056ed36eL, 0x503c28286c443c44L,
        0x5579a7a72c8b798bL, 0x63e2bcbc813de23dL, 0x2c1d161631271d27L, 0x4176adad379a769aL,
        0xad3bdbdb964d3b4dL, 0xc85664649efa56faL, 0xe84e7474a6d24ed2L, 0x281e141436221e22L,
        0x3fdb9292e476db76L, 0x180a0c0c121e0a1eL, 0x906c4848fcb46cb4L, 0x6be4b8b88f37e437L,
        0x255d9f9f78e75de7L, 0x616ebdbd0fb26eb2L, 0x86ef4343692aef2aL, 0x93a6c4c435f1a6f1L,
        0x72a83939dae3a8e3L, 0x62a43131c6f7a4f7L, 0xbd37d3d38a593759L, 0xff8bf2f274868b86L,
        0xb132d5d583563256L, 0x0d438b8b4ec543c5L, 0xdc596e6e85eb59ebL, 0xafb7dada18c2b7c2L,
        0x028c01018e8f8c8fL, 0x7964b1b11dac64acL, 0x23d29c9cf16dd26dL, 0x92e04949723be03bL,
        0xabb4d8d81fc7b4c7L, 0x43faacacb915fa15L, 0xfd07f3f3fa090709L, 0x8525cfcfa06f256fL,
        0x8fafcaca20eaafeaL, 0xf38ef4f47d898e89L, 0x8ee947476720e920L, 0x2018101038281828L,
        0xded56f6f0b64d564L, 0xfb88f0f073838883L, 0x946f4a4afbb16fb1L, 0xb8725c5cca967296L,
        0x70243838546c246cL, 0xaef157575f08f108L, 0xe6c773732152c752L, 0x3551979764f351f3L,
        0x8d23cbcbae652365L, 0x597ca1a125847c84L, 0xcb9ce8e857bf9cbfL, 0x7c213e3e5d632163L,
        0x37dd9696ea7cdd7cL, 0xc2dc61611e7fdc7fL, 0x1a860d0d9c918691L, 0x1e850f0f9b948594L,
        0xdb90e0e04bab90abL, 0xf8427c7cbac642c6L, 0xe2c471712657c457L, 0x83aacccc29e5aae5L,
        0x3bd89090e373d873L, 0x0c050606090f050fL, 0xf501f7f7f4030103L, 0x38121c1c2a361236L,
        0x9fa3c2c23cfea3feL, 0xd45f6a6a8be15fe1L, 0x47f9aeaebe10f910L, 0xd2d06969026bd06bL,
        0x2e911717bfa891a8L, 0x2958999971e858e8L, 0x74273a3a53692769L, 0x4eb92727f7d0b9d0L,
        0xa938d9d991483848L, 0xcd13ebebde351335L, 0x56b32b2be5ceb3ceL, 0x4433222277553355L,
        0xbfbbd2d204d6bbd6L, 0x4970a9a939907090L, 0x0e89070787808980L, 0x66a73333c1f2a7f2L,
        0x5ab62d2decc1b6c1L, 0x78223c3c5a662266L, 0x2a921515b8ad92adL, 0x8920c9c9a9602060L,
        0x154987875cdb49dbL, 0x4fffaaaab01aff1aL, 0xa0785050d8887888L, 0x517aa5a52b8e7a8eL,
        0x068f0303898a8f8aL, 0xb2f859594a13f813L, 0x12800909929b809bL, 0x34171a1a23391739L,
        0xcada65651075da75L, 0xb531d7d784533153L, 0x13c68484d551c651L, 0xbbb8d0d003d3b8d3L,
        0x1fc38282dc5ec35eL, 0x52b02929e2cbb0cbL, 0xb4775a5ac3997799L, 0x3c111e1e2d331133L,
        0xf6cb7b7b3d46cb46L, 0x4bfca8a8b71ffc1fL, 0xdad66d6d0c61d661L, 0x583a2c2c624e3a4eL
    };

    private static final long[] T4 = {
        0xf497a5c6c632f4a5L, 0x97eb84f8f86f9784L, 0xb0c799eeee5eb099L, 0x8cf78df6f67a8c8dL,
        0x17e50dffffe8170dL, 0xdcb7bdd6d60adcbdL, 0xc8a7b1dede16c8b1L, 0xfc395491916dfc54L,
        0xf0c050606090f050L, 0x0504030202070503L, 0xe087a9cece2ee0a9L, 0x87ac7d5656d1877dL,
        0x2bd519e7e7cc2b19L, 0xa67162b5b513a662L, 0x319ae64d4d7c31e6L, 0xb5c39aecec59b59aL,
        0xcf05458f8f40cf45L, 0xbc3e9d1f1fa3bc9dL, 0xc00940898949c040L, 0x92ef87fafa689287L,
        0x3fc515efefd03f15L, 0x267febb2b29426ebL, 0x4007c98e8ece40c9L, 0x1ded0bfbfbe61d0bL,
        0x2f82ec41416e2fecL, 0xa97d67b3b31aa967L, 0x1cbefd5f5f431cfdL, 0x258aea45456025eaL,
        0xda46bf2323f9dabfL, 0x02a6f753535102f7L, 0xa1d396e4e445a196L, 0xed2d5b9b9b76ed5bL,
        0x5deac27575285dc2L, 0x24d91ce1e1c5241cL, 0xe97aae3d3dd4e9aeL, 0xbe986a4c4cf2be6aL,
        0xeed85a6c6c82ee5aL, 0xc3fc417e7ebdc341L, 0x06f102f5f5f30602L, 0xd11d4f838352d14fL,
        0xe4d05c68688ce45cL, 0x07a2f451515607f4L, 0x5cb934d1d18d5c34L, 0x18e908f9f9e11808L,
        0xaedf93e2e24cae93L, 0x954d73abab3e9573L, 0xf5c453626297f553L, 0x41543f2a2a6b413fL,
        0x14100c08081c140cL, 0xf63152959563f652L, 0xaf8c654646e9af65L, 0xe2215e9d9d7fe25eL,
        0x7860283030487828L, 0xf86ea13737cff8a1L, 0x11140f0a0a1b110fL, 0xc45eb52f2febc4b5L,
        0x1b1c090e0e151b09L, 0x5a483624247e5a36L, 0xb6369b1b1badb69bL, 0x47a53ddfdf98473dL,
        0x6a8126cdcda76a26L, 0xbb9c694e4ef5bb69L, 0x4cfecd7f7f334ccdL, 0xbacf9feaea50ba9fL,
        0x2d241b12123f2d1bL, 0xb93a9e1d1da4b99eL, 0x9cb0745858c49c74L, 0x72682e343446722eL,
        0x776c2d363641772dL, 0xcda3b2dcdc11cdb2L, 0x2973eeb4b49d29eeL, 0x16b6fb5b5b4d16fbL,
        0x0153f6a4a4a501f6L, 0xd7ec4d7676a1d74dL, 0xa37561b7b714a361L, 0x49face7d7d3449ceL,
        0x8da47b5252df8d7bL, 0x42a13edddd9f423eL, 0x93bc715e5ecd9371L, 0xa226971313b1a297L,
        0x0457f5a6a6a204f5L, 0xb86968b9b901b868L, 0x0000000000000000L, 0x74992cc1c1b5742cL,
        0xa080604040e0a060L, 0x21dd1fe3e3c2211fL, 0x43f2c879793a43c8L, 0x2c77edb6b69a2cedL,
        0xd9b3bed4d40dd9beL, 0xca01468d8d47ca46L, 0x70ced967671770d9L, 0xdde44b7272afdd4bL,
        0x7933de9494ed79deL, 0x672bd49898ff67d4L, 0x237be8b0b09323e8L, 0xde114a85855bde4aL,
        0xbd6d6bbbbb06bd6bL, 0x7e912ac5c5bb7e2aL, 0x349ee54f4f7b34e5L, 0x3ac116ededd73a16L,
        0x5417c58686d254c5L, 0x622fd79a9af862d7L, 0xffcc55666699ff55L, 0xa722941111b6a794L,
        0x4a0fcf8a8ac04acfL, 0x30c910e9e9d93010L, 0x0a080604040e0a06L, 0x98e781fefe669881L,
        0x0b5bf0a0a0ab0bf0L, 0xccf0447878b4cc44L, 0xd54aba2525f0d5baL, 0x3e96e34b4b753ee3L,
        0x0e5ff3a2a2ac0ef3L, 0x19bafe5d5d4419feL, 0x5b1bc08080db5bc0L, 0x850a8a050580858aL,
        0xec7ead3f3fd3ecadL, 0xdf42bc2121fedfbcL, 0xd8e0487070a8d848L, 0x0cf904f1f1fd0c04L,
        0x7ac6df6363197adfL, 0x58eec177772f58c1L, 0x9f4575afaf309f75L, 0xa584634242e7a563L,
        0x5040302020705030L, 0x2ed11ae5e5cb2e1aL, 0x12e10efdfdef120eL, 0xb7656dbfbf08b76dL,
        0xd4194c818155d44cL, 0x3c30141818243c14L, 0x5f4c352626795f35L, 0x719d2fc3c3b2712fL,
        0x3867e1bebe8638e1L, 0xfd6aa23535c8fda2L, 0x4f0bcc8888c74fccL, 0x4b5c392e2e654b39L,
        0xf93d5793936af957L, 0x0daaf25555580df2L, 0x9de382fcfc619d82L, 0xc9f4477a7ab3c947L,
        0xef8bacc8c827efacL, 0x326fe7baba8832e7L, 0x7d642b32324f7d2bL, 0xa4d795e6e642a495L,
        0xfb9ba0c0c03bfba0L, 0xb332981919aab398L, 0x6827d19e9ef668d1L, 0x815d7fa3a322817fL,
        0xaa88664444eeaa66L, 0x82a87e5454d6827eL, 0xe676ab3b3bdde6abL, 0x9e16830b0b959e83L,
        0x4503ca8c8cc945caL, 0x7b9529c7c7bc7b29L, 0x6ed6d36b6b056ed3L, 0x44503c28286c443cL,
        0x8b5579a7a72c8b79L, 0x3d63e2bcbc813de2L, 0x272c1d161631271dL, 0x9a4176adad379a76L,
        0x4dad3bdbdb964d3bL, 0xfac85664649efa56L, 0xd2e84e7474a6d24eL, 0x22281e141436221eL,
        0x763fdb9292e476dbL, 0x1e180a0c0c121e0aL, 0xb4906c4848fcb46cL, 0x376be4b8b88f37e4L,
        0xe7255d9f9f78e75dL, 0xb2616ebdbd0fb26eL, 0x2a86ef4343692aefL, 0xf193a6c4c435f1a6L,
        0xe372a83939dae3a8L, 0xf762a43131c6f7a4L, 0x59bd37d3d38a5937L, 0x86ff8bf2f274868bL,
        0x56b132d5d5835632L, 0xc50d438b8b4ec543L, 0xebdc596e6e85eb59L, 0xc2afb7dada18c2b7L,
        0x8f028c01018e8f8cL, 0xac7964b1b11dac64L, 0x6d23d29c9cf16dd2L, 0x3b92e04949723be0L,
        0xc7abb4d8d81fc7b4L, 0x1543faacacb915faL, 0x09fd07f3f3fa0907L, 0x6f8525cfcfa06f25L,
        0xea8fafcaca20eaafL, 0x89f38ef4f47d898eL, 0x208ee947476720e9L, 0x2820181010382818L,
        0x64ded56f6f0b64d5L, 0x83fb88f0f0738388L, 0xb1946f4a4afbb16fL, 0x96b8725c5cca9672L,
        0x6c70243838546c24L, 0x08aef157575f08f1L, 0x52e6c773732152c7L, 0xf33551979764f351L,
        0x658d23cbcbae6523L, 0x84597ca1a125847cL, 0xbfcb9ce8e857bf9cL, 0x637c213e3e5d6321L,
        0x7c37dd9696ea7cddL, 0x7fc2dc61611e7fdcL, 0x911a860d0d9c9186L, 0x941e850f0f9b9485L,
        0xabdb90e0e04bab90L, 0xc6f8427c7cbac642L, 0x57e2c471712657c4L, 0xe583aacccc29e5aaL,
        0x733bd89090e373d8L, 0x0f0c050606090f05L, 0x03f501f7f7f40301L, 0x3638121c1c2a3612L,
        0xfe9fa3c2c23cfea3L, 0xe1d45f6a6a8be15fL, 0x1047f9aeaebe10f9L, 0x6bd2d06969026bd0L,
        0xa82e911717bfa891L, 0xe82958999971e858L, 0x6974273a3a536927L, 0xd04eb92727f7d0b9L,
        0x48a938d9d9914838L, 0x35cd13ebebde3513L, 0xce56b32b2be5ceb3L, 0x5544332222775533L,
        0xd6bfbbd2d204d6bbL, 0x904970a9a9399070L, 0x800e890707878089L, 0xf266a73333c1f2a7L,
        0xc15ab62d2decc1b6L, 0x6678223c3c5a6622L, 0xad2a921515b8ad92L, 0x608920c9c9a96020L,
        0xdb154987875cdb49L, 0x1a4fffaaaab01affL, 0x88a0785050d88878L, 0x8e517aa5a52b8e7aL,
        0x8a068f0303898a8fL, 0x13b2f859594a13f8L, 0x9b12800909929b80L, 0x3934171a1a233917L,
        0x75cada65651075daL, 0x53b531d7d7845331L, 0x5113c68484d551c6L, 0xd3bbb8d0d003d3b8L,
        0x5e1fc38282dc5ec3L, 0xcb52b02929e2cbb0L, 0x99b4775a5ac39977L, 0x333c111e1e2d3311L,
        0x46f6cb7b7b3d46cbL, 0x1f4bfca8a8b71ffcL, 0x61dad66d6d0c61d6L, 0x4e583a2c2c624e3aL
    };

    private static final long[] T5 = {
        0xa5f497a5c6c632f4L, 0x8497eb84f8f86f97L, 0x99b0c799eeee5eb0L, 0x8d8cf78df6f67a8cL,
        0x0d17e50dffffe817L, 0xbddcb7bdd6d60adcL, 0xb1c8a7b1dede16c8L, 0x54fc395491916dfcL,
        0x50f0c050606090f0L, 0x0305040302020705L, 0xa9e087a9cece2ee0L, 0x7d87ac7d5656d187L,
        0x192bd519e7e7cc2bL, 0x62a67162b5b513a6L, 0xe6319ae64d4d7c31L, 0x9ab5c39aecec59b5L,
        0x45cf05458f8f40cfL, 0x9dbc3e9d1f1fa3bcL, 0x40c00940898949c0L, 0x8792ef87fafa6892L,
        0x153fc515efefd03fL, 0xeb267febb2b29426L, 0xc94007c98e8ece40L, 0x0b1ded0bfbfbe61dL,
        0xec2f82ec41416e2fL, 0x67a97d67b3b31aa9L, 0xfd1cbefd5f5f431cL, 0xea258aea45456025L,
        0xbfda46bf2323f9daL, 0xf702a6f753535102L, 0x96a1d396e4e445a1L, 0x5bed2d5b9b9b76edL,
        0xc25deac27575285dL, 0x1c24d91ce1e1c524L, 0xaee97aae3d3dd4e9L, 0x6abe986a4c4cf2beL,
        0x5aeed85a6c6c82eeL, 0x41c3fc417e7ebdc3L, 0x0206f102f5f5f306L, 0x4fd11d4f838352d1L,
        0x5ce4d05c68688ce4L, 0xf407a2f451515607L, 0x345cb934d1d18d5cL, 0x0818e908f9f9e118L,
        0x93aedf93e2e24caeL, 0x73954d73abab3e95L, 0x53f5c453626297f5L, 0x3f41543f2a2a6b41L,
        0x0c14100c08081c14L, 0x52f63152959563f6L, 0x65af8c654646e9afL, 0x5ee2215e9d9d7fe2L,
        0x2878602830304878L, 0xa1f86ea13737cff8L, 0x0f11140f0a0a1b11L, 0xb5c45eb52f2febc4L,
        0x091b1c090e0e151bL, 0x365a483624247e5aL, 0x9bb6369b1b1badb6L, 0x3d47a53ddfdf9847L,
        0x266a8126cdcda76aL, 0x69bb9c694e4ef5bbL, 0xcd4cfecd7f7f334cL, 0x9fbacf9feaea50baL,
        0x1b2d241b12123f2dL, 0x9eb93a9e1d1da4b9L, 0x749cb0745858c49cL, 0x2e72682e34344672L,
        0x2d776c2d36364177L, 0xb2cda3b2dcdc11cdL, 0xee2973eeb4b49d29L, 0xfb16b6fb5b5b4d16L,
        0xf60153f6a4a4a501L, 0x4dd7ec4d7676a1d7L, 0x61a37561b7b714a3L, 0xce49face7d7d3449L,
        0x7b8da47b5252df8dL, 0x3e42a13edddd9f42L, 0x7193bc715e5ecd93L, 0x97a226971313b1a2L,
        0xf50457f5a6a6a204L, 0x68b86968b9b901b8L, 0x0000000000000000L, 0x2c74992cc1c1b574L,
        0x60a080604040e0a0L, 0x1f21dd1fe3e3c221L, 0xc843f2c879793a43L, 0xed2c77edb6b69a2cL,
        0xbed9b3bed4d40dd9L, 0x46ca01468d8d47caL, 0xd970ced967671770L, 0x4bdde44b7272afddL,
        0xde7933de9494ed79L, 0xd4672bd49898ff67L, 0xe8237be8b0b09323L, 0x4ade114a85855bdeL,
        0x6bbd6d6bbbbb06bdL, 0x2a7e912ac5c5bb7eL, 0xe5349ee54f4f7b34L, 0x163ac116ededd73aL,
        0xc55417c58686d254L, 0xd7622fd79a9af862L, 0x55ffcc55666699ffL, 0x94a722941111b6a7L,
        0xcf4a0fcf8a8ac04aL, 0x1030c910e9e9d930L, 0x060a080604040e0aL, 0x8198e781fefe6698L,
        0xf00b5bf0a0a0ab0bL, 0x44ccf0447878b4ccL, 0xbad54aba2525f0d5L, 0xe33e96e34b4b753eL,
        0xf30e5ff3a2a2ac0eL, 0xfe19bafe5d5d4419L, 0xc05b1bc08080db5bL, 0x8a850a8a05058085L,
        0xadec7ead3f3fd3ecL, 0xbcdf42bc2121fedfL, 0x48d8e0487070a8d8L, 0x040cf904f1f1fd0cL,
        0xdf7ac6df6363197aL, 0xc158eec177772f58L, 0x759f4575afaf309fL, 0x63a584634242e7a5L,
        0x3050403020207050L, 0x1a2ed11ae5e5cb2eL, 0x0e12e10efdfdef12L, 0x6db7656dbfbf08b7L,
        0x4cd4194c818155d4L, 0x143c30141818243cL, 0x355f4c352626795fL, 0x2f719d2fc3c3b271L,
        0xe13867e1bebe8638L, 0xa2fd6aa23535c8fdL, 0xcc4f0bcc8888c74fL, 0x394b5c392e2e654bL,
        0x57f93d5793936af9L, 0xf20daaf25555580dL, 0x829de382fcfc619dL, 0x47c9f4477a7ab3c9L,
        0xacef8bacc8c827efL, 0xe7326fe7baba8832L, 0x2b7d642b32324f7dL, 0x95a4d795e6e642a4L,
        0xa0fb9ba0c0c03bfbL, 0x98b332981919aab3L, 0xd16827d19e9ef668L, 0x7f815d7fa3a32281L,
        0x66aa88664444eeaaL, 0x7e82a87e5454d682L, 0xabe676ab3b3bdde6L, 0x839e16830b0b959eL,
        0xca4503ca8c8cc945L, 0x297b9529c7c7bc7bL, 0xd36ed6d36b6b056eL, 0x3c44503c28286c44L,
        0x798b5579a7a72c8bL, 0xe23d63e2bcbc813dL, 0x1d272c1d16163127L, 0x769a4176adad379aL,
        0x3b4dad3bdbdb964dL, 0x56fac85664649efaL, 0x4ed2e84e7474a6d2L, 0x1e22281e14143622L,
        0xdb763fdb9292e476L, 0x0a1e180a0c0c121eL, 0x6cb4906c4848fcb4L, 0xe4376be4b8b88f37L,
        0x5de7255d9f9f78e7L, 0x6eb2616ebdbd0fb2L, 0xef2a86ef4343692aL, 0xa6f193a6c4c435f1L,
        0xa8e372a83939dae3L, 0xa4f762a43131c6f7L, 0x3759bd37d3d38a59L, 0x8b86ff8bf2f27486L,
        0x3256b132d5d58356L, 0x43c50d438b8b4ec5L, 0x59ebdc596e6e85ebL, 0xb7c2afb7dada18c2L,
        0x8c8f028c01018e8fL, 0x64ac7964b1b11dacL, 0xd26d23d29c9cf16dL, 0xe03b92e04949723bL,
        0xb4c7abb4d8d81fc7L, 0xfa1543faacacb915L, 0x0709fd07f3f3fa09L, 0x256f8525cfcfa06fL,
        0xafea8fafcaca20eaL, 0x8e89f38ef4f47d89L, 0xe9208ee947476720L, 0x1828201810103828L,
        0xd564ded56f6f0b64L, 0x8883fb88f0f07383L, 0x6fb1946f4a4afbb1L, 0x7296b8725c5cca96L,
        0x246c70243838546cL, 0xf108aef157575f08L, 0xc752e6c773732152L, 0x51f33551979764f3L,
        0x23658d23cbcbae65L, 0x7c84597ca1a12584L, 0x9cbfcb9ce8e857bfL, 0x21637c213e3e5d63L,
        0xdd7c37dd9696ea7cL, 0xdc7fc2dc61611e7fL, 0x86911a860d0d9c91L, 0x85941e850f0f9b94L,
        0x90abdb90e0e04babL, 0x42c6f8427c7cbac6L, 0xc457e2c471712657L, 0xaae583aacccc29e5L,
        0xd8733bd89090e373L, 0x050f0c050606090fL, 0x0103f501f7f7f403L, 0x123638121c1c2a36L,
        0xa3fe9fa3c2c23cfeL, 0x5fe1d45f6a6a8be1L, 0xf91047f9aeaebe10L, 0xd06bd2d06969026bL,
        0x91a82e911717bfa8L, 0x58e82958999971e8L, 0x276974273a3a5369L, 0xb9d04eb92727f7d0L,
        0x3848a938d9d99148L, 0x1335cd13ebebde35L, 0xb3ce56b32b2be5ceL, 0x3355443322227755L,
        0xbbd6bfbbd2d204d6L, 0x70904970a9a93990L, 0x89800e8907078780L, 0xa7f266a73333c1f2L,
        0xb6c15ab62d2decc1L, 0x226678223c3c5a66L, 0x92ad2a921515b8adL, 0x20608920c9c9a960L,
        0x49db154987875cdbL, 0xff1a4fffaaaab01aL, 0x7888a0785050d888L, 0x7a8e517aa5a52b8eL,
        0x8f8a068f0303898aL, 0xf813b2f859594a13L, 0x809b12800909929bL, 0x173934171a1a2339L,
        0xda75cada65651075L, 0x3153b531d7d78453L, 0xc65113c68484d551L, 0xb8d3bbb8d0d003d3L,
        0xc35e1fc38282dc5eL, 0xb0cb52b02929e2cbL, 0x7799b4775a5ac399L, 0x11333c111e1e2d33L,
        0xcb46f6cb7b7b3d46L, 0xfc1f4bfca8a8b71fL, 0xd661dad66d6d0c61L, 0x3a4e583a2c2c624eL
    };

    private static final long[] T6 = {
        0xf4a5f497a5c6c632L, 0x978497eb84f8f86fL, 0xb099b0c799eeee5eL, 0x8c8d8cf78df6f67aL,
        0x170d17e50dffffe8L, 0xdcbddcb7bdd6d60aL, 0xc8b1c8a7b1dede16L, 0xfc54fc395491916dL,
        0xf050f0c050606090L, 0x0503050403020207L, 0xe0a9e087a9cece2eL, 0x877d87ac7d5656d1L,
        0x2b192bd519e7e7ccL, 0xa662a67162b5b513L, 0x31e6319ae64d4d7cL, 0xb59ab5c39aecec59L,
        0xcf45cf05458f8f40L, 0xbc9dbc3e9d1f1fa3L, 0xc040c00940898949L, 0x928792ef87fafa68L,
        0x3f153fc515efefd0L, 0x26eb267febb2b294L, 0x40c94007c98e8eceL, 0x1d0b1ded0bfbfbe6L,
        0x2fec2f82ec41416eL, 0xa967a97d67b3b31aL, 0x1cfd1cbefd5f5f43L, 0x25ea258aea454560L,
        0xdabfda46bf2323f9L, 0x02f702a6f7535351L, 0xa196a1d396e4e445L, 0xed5bed2d5b9b9b76L,
        0x5dc25deac2757528L, 0x241c24d91ce1e1c5L, 0xe9aee97aae3d3dd4L, 0xbe6abe986a4c4cf2L,
        0xee5aeed85a6c6c82L, 0xc341c3fc417e7ebdL, 0x060206f102f5f5f3L, 0xd14fd11d4f838352L,
        0xe45ce4d05c68688cL, 0x07f407a2f4515156L, 0x5c345cb934d1d18dL, 0x180818e908f9f9e1L,
        0xae93aedf93e2e24cL, 0x9573954d73abab3eL, 0xf553f5c453626297L, 0x413f41543f2a2a6bL,
        0x140c14100c08081cL, 0xf652f63152959563L, 0xaf65af8c654646e9L, 0xe25ee2215e9d9d7fL,
        0x7828786028303048L, 0xf8a1f86ea13737cfL, 0x110f11140f0a0a1bL, 0xc4b5c45eb52f2febL,
        0x1b091b1c090e0e15L, 0x5a365a483624247eL, 0xb69bb6369b1b1badL, 0x473d47a53ddfdf98L,
        0x6a266a8126cdcda7L, 0xbb69bb9c694e4ef5L, 0x4ccd4cfecd7f7f33L, 0xba9fbacf9feaea50L,
        0x2d1b2d241b12123fL, 0xb99eb93a9e1d1da4L, 0x9c749cb0745858c4L, 0x722e72682e343446L,
        0x772d776c2d363641L, 0xcdb2cda3b2dcdc11L, 0x29ee2973eeb4b49dL, 0x16fb16b6fb5b5b4dL,
        0x01f60153f6a4a4a5L, 0xd74dd7ec4d7676a1L, 0xa361a37561b7b714L, 0x49ce49face7d7d34L,
        0x8d7b8da47b5252dfL, 0x423e42a13edddd9fL, 0x937193bc715e5ecdL, 0xa297a226971313b1L,
        0x04f50457f5a6a6a2L, 0xb868b86968b9b901L, 0x0000000000000000L, 0x742c74992cc1c1b5L,
        0xa060a080604040e0L, 0x211f21dd1fe3e3c2L, 0x43c843f2c879793aL, 0x2ced2c77edb6b69aL,
        0xd9bed9b3bed4d40dL, 0xca46ca01468d8d47L, 0x70d970ced9676717L, 0xdd4bdde44b7272afL,
        0x79de7933de9494edL, 0x67d4672bd49898ffL, 0x23e8237be8b0b093L, 0xde4ade114a85855bL,
        0xbd6bbd6d6bbbbb06L, 0x7e2a7e912ac5c5bbL, 0x34e5349ee54f4f7bL, 0x3a163ac116ededd7L,
        0x54c55417c58686d2L, 0x62d7622fd79a9af8L, 0xff55ffcc55666699L, 0xa794a722941111b6L,
        0x4acf4a0fcf8a8ac0L, 0x301030c910e9e9d9L, 0x0a060a080604040eL, 0x988198e781fefe66L,
        0x0bf00b5bf0a0a0abL, 0xcc44ccf0447878b4L, 0xd5bad54aba2525f0L, 0x3ee33e96e34b4b75L,
        0x0ef30e5ff3a2a2acL, 0x19fe19bafe5d5d44L, 0x5bc05b1bc08080dbL, 0x858a850a8a050580L,
        0xecadec7ead3f3fd3L, 0xdfbcdf42bc2121feL, 0xd848d8e0487070a8L, 0x0c040cf904f1f1fdL,
        0x7adf7ac6df636319L, 0x58c158eec177772fL, 0x9f759f4575afaf30L, 0xa563a584634242e7L,
        0x5030504030202070L, 0x2e1a2ed11ae5e5cbL, 0x120e12e10efdfdefL, 0xb76db7656dbfbf08L,
        0xd44cd4194c818155L, 0x3c143c3014181824L, 0x5f355f4c35262679L, 0x712f719d2fc3c3b2L,
        0x38e13867e1bebe86L, 0xfda2fd6aa23535c8L, 0x4fcc4f0bcc8888c7L, 0x4b394b5c392e2e65L,
        0xf957f93d5793936aL, 0x0df20daaf2555558L, 0x9d829de382fcfc61L, 0xc947c9f4477a7ab3L,
        0xefacef8bacc8c827L, 0x32e7326fe7baba88L, 0x7d2b7d642b32324fL, 0xa495a4d795e6e642L,
        0xfba0fb9ba0c0c03bL, 0xb398b332981919aaL, 0x68d16827d19e9ef6L, 0x817f815d7fa3a322L,
        0xaa66aa88664444eeL, 0x827e82a87e5454d6L, 0xe6abe676ab3b3bddL, 0x9e839e16830b0b95L,
        0x45ca4503ca8c8cc9L, 0x7b297b9529c7c7bcL, 0x6ed36ed6d36b6b05L, 0x443c44503c28286cL,
        0x8b798b5579a7a72cL, 0x3de23d63e2bcbc81L, 0x271d272c1d161631L, 0x9a769a4176adad37L,
        0x4d3b4dad3bdbdb96L, 0xfa56fac85664649eL, 0xd24ed2e84e7474a6L, 0x221e22281e141436L,
        0x76db763fdb9292e4L, 0x1e0a1e180a0c0c12L, 0xb46cb4906c4848fcL, 0x37e4376be4b8b88fL,
        0xe75de7255d9f9f78L, 0xb26eb2616ebdbd0fL, 0x2aef2a86ef434369L, 0xf1a6f193a6c4c435L,
        0xe3a8e372a83939daL, 0xf7a4f762a43131c6L, 0x593759bd37d3d38aL, 0x868b86ff8bf2f274L,
        0x563256b132d5d583L, 0xc543c50d438b8b4eL, 0xeb59ebdc596e6e85L, 0xc2b7c2afb7dada18L,
        0x8f8c8f028c01018eL, 0xac64ac7964b1b11dL, 0x6dd26d23d29c9cf1L, 0x3be03b92e0494972L,
        0xc7b4c7abb4d8d81fL, 0x15fa1543faacacb9L, 0x090709fd07f3f3faL, 0x6f256f8525cfcfa0L,
        0xeaafea8fafcaca20L, 0x898e89f38ef4f47dL, 0x20e9208ee9474767L, 0x2818282018101038L,
        0x64d564ded56f6f0bL, 0x838883fb88f0f073L, 0xb16fb1946f4a4afbL, 0x967296b8725c5ccaL,
        0x6c246c7024383854L, 0x08f108aef157575fL, 0x52c752e6c7737321L, 0xf351f33551979764L,
        0x6523658d23cbcbaeL, 0x847c84597ca1a125L, 0xbf9cbfcb9ce8e857L, 0x6321637c213e3e5dL,
        0x7cdd7c37dd9696eaL, 0x7fdc7fc2dc61611eL, 0x9186911a860d0d9cL, 0x9485941e850f0f9bL,
        0xab90abdb90e0e04bL, 0xc642c6f8427c7cbaL, 0x57c457e2c4717126L, 0xe5aae583aacccc29L,
        0x73d8733bd89090e3L, 0x0f050f0c05060609L, 0x030103f501f7f7f4L, 0x36123638121c1c2aL,
        0xfea3fe9fa3c2c23cL, 0xe15fe1d45f6a6a8bL, 0x10f91047f9aeaebeL, 0x6bd06bd2d0696902L,
        0xa891a82e911717bfL, 0xe858e82958999971L, 0x69276974273a3a53L, 0xd0b9d04eb92727f7L,
        0x483848a938d9d991L, 0x351335cd13ebebdeL, 0xceb3ce56b32b2be5L, 0x5533554433222277L,
        0xd6bbd6bfbbd2d204L, 0x9070904970a9a939L, 0x8089800e89070787L, 0xf2a7f266a73333c1L,
        0xc1b6c15ab62d2decL, 0x66226678223c3c5aL, 0xad92ad2a921515b8L, 0x6020608920c9c9a9L,
        0xdb49db154987875cL, 0x1aff1a4fffaaaab0L, 0x887888a0785050d8L, 0x8e7a8e517aa5a52bL,
        0x8a8f8a068f030389L, 0x13f813b2f859594aL, 0x9b809b1280090992L, 0x39173934171a1a23L,
        0x75da75cada656510L, 0x533153b531d7d784L, 0x51c65113c68484d5L, 0xd3b8d3bbb8d0d003L,
        0x5ec35e1fc38282dcL, 0xcbb0cb52b02929e2L, 0x997799b4775a5ac3L, 0x3311333c111e1e2dL,
        0x46cb46f6cb7b7b3dL, 0x1ffc1f4bfca8a8b7L, 0x61d661dad66d6d0cL, 0x4e3a4e583a2c2c62L
    };

    private static final long[] T7 = {
        0x32f4a5f497a5c6c6L, 0x6f978497eb84f8f8L, 0x5eb099b0c799eeeeL, 0x7a8c8d8cf78df6f6L,
        0xe8170d17e50dffffL, 0x0adcbddcb7bdd6d6L, 0x16c8b1c8a7b1dedeL, 0x6dfc54fc39549191L,
        0x90f050f0c0506060L, 0x0705030504030202L, 0x2ee0a9e087a9ceceL, 0xd1877d87ac7d5656L,
        0xcc2b192bd519e7e7L, 0x13a662a67162b5b5L, 0x7c31e6319ae64d4dL, 0x59b59ab5c39aececL,
        0x40cf45cf05458f8fL, 0xa3bc9dbc3e9d1f1fL, 0x49c040c009408989L, 0x68928792ef87fafaL,
        0xd03f153fc515efefL, 0x9426eb267febb2b2L, 0xce40c94007c98e8eL, 0xe61d0b1ded0bfbfbL,
        0x6e2fec2f82ec4141L, 0x1aa967a97d67b3b3L, 0x431cfd1cbefd5f5fL, 0x6025ea258aea4545L,
        0xf9dabfda46bf2323L, 0x5102f702a6f75353L, 0x45a196a1d396e4e4L, 0x76ed5bed2d5b9b9bL,
        0x285dc25deac27575L, 0xc5241c24d91ce1e1L, 0xd4e9aee97aae3d3dL, 0xf2be6abe986a4c4cL,
        0x82ee5aeed85a6c6cL, 0xbdc341c3fc417e7eL, 0xf3060206f102f5f5L, 0x52d14fd11d4f8383L,
        0x8ce45ce4d05c6868L, 0x5607f407a2f45151L, 0x8d5c345cb934d1d1L, 0xe1180818e908f9f9L,
        0x4cae93aedf93e2e2L, 0x3e9573954d73ababL, 0x97f553f5c4536262L, 0x6b413f41543f2a2aL,
        0x1c140c14100c0808L, 0x63f652f631529595L, 0xe9af65af8c654646L, 0x7fe25ee2215e9d9dL,
        0x4878287860283030L, 0xcff8a1f86ea13737L, 0x1b110f11140f0a0aL, 0xebc4b5c45eb52f2fL,
        0x151b091b1c090e0eL, 0x7e5a365a48362424L, 0xadb69bb6369b1b1bL, 0x98473d47a53ddfdfL,
        0xa76a266a8126cdcdL, 0xf5bb69bb9c694e4eL, 0x334ccd4cfecd7f7fL, 0x50ba9fbacf9feaeaL,
        0x3f2d1b2d241b1212L, 0xa4b99eb93a9e1d1dL, 0xc49c749cb0745858L, 0x46722e72682e3434L,
        0x41772d776c2d3636L, 0x11cdb2cda3b2dcdcL, 0x9d29ee2973eeb4b4L, 0x4d16fb16b6fb5b5bL,
        0xa501f60153f6a4a4L, 0xa1d74dd7ec4d7676L, 0x14a361a37561b7b7L, 0x3449ce49face7d7dL,
        0xdf8d7b8da47b5252L, 0x9f423e42a13eddddL, 0xcd937193bc715e5eL, 0xb1a297a226971313L,
        0xa204f50457f5a6a6L, 0x01b868b86968b9b9L, 0x0000000000000000L, 0xb5742c74992cc1c1L,
        0xe0a060a080604040L, 0xc2211f21dd1fe3e3L, 0x3a43c843f2c87979L, 0x9a2ced2c77edb6b6L,
        0x0dd9bed9b3bed4d4L, 0x47ca46ca01468d8dL, 0x1770d970ced96767L, 0xafdd4bdde44b7272L,
        0xed79de7933de9494L, 0xff67d4672bd49898L, 0x9323e8237be8b0b0L, 0x5bde4ade114a8585L,
        0x06bd6bbd6d6bbbbbL, 0xbb7e2a7e912ac5c5L, 0x7b34e5349ee54f4fL, 0xd73a163ac116ededL,
        0xd254c55417c58686L, 0xf862d7622fd79a9aL, 0x99ff55ffcc556666L, 0xb6a794a722941111L,
        0xc04acf4a0fcf8a8aL, 0xd9301030c910e9e9L, 0x0e0a060a08060404L, 0x66988198e781fefeL,
        0xab0bf00b5bf0a0a0L, 0xb4cc44ccf0447878L, 0xf0d5bad54aba2525L, 0x753ee33e96e34b4bL,
        0xac0ef30e5ff3a2a2L, 0x4419fe19bafe5d5dL, 0xdb5bc05b1bc08080L, 0x80858a850a8a0505L,
        0xd3ecadec7ead3f3fL, 0xfedfbcdf42bc2121L, 0xa8d848d8e0487070L, 0xfd0c040cf904f1f1L,
        0x197adf7ac6df6363L, 0x2f58c158eec17777L, 0x309f759f4575afafL, 0xe7a563a584634242L,
        0x7050305040302020L, 0xcb2e1a2ed11ae5e5L, 0xef120e12e10efdfdL, 0x08b76db7656dbfbfL,
        0x55d44cd4194c8181L, 0x243c143c30141818L, 0x795f355f4c352626L, 0xb2712f719d2fc3c3L,
        0x8638e13867e1bebeL, 0xc8fda2fd6aa23535L, 0xc74fcc4f0bcc8888L, 0x654b394b5c392e2eL,
        0x6af957f93d579393L, 0x580df20daaf25555L, 0x619d829de382fcfcL, 0xb3c947c9f4477a7aL,
        0x27efacef8bacc8c8L, 0x8832e7326fe7babaL, 0x4f7d2b7d642b3232L, 0x42a495a4d795e6e6L,
        0x3bfba0fb9ba0c0c0L, 0xaab398b332981919L, 0xf668d16827d19e9eL, 0x22817f815d7fa3a3L,
        0xeeaa66aa88664444L, 0xd6827e82a87e5454L, 0xdde6abe676ab3b3bL, 0x959e839e16830b0bL,
        0xc945ca4503ca8c8cL, 0xbc7b297b9529c7c7L, 0x056ed36ed6d36b6bL, 0x6c443c44503c2828L,
        0x2c8b798b5579a7a7L, 0x813de23d63e2bcbcL, 0x31271d272c1d1616L, 0x379a769a4176adadL,
        0x964d3b4dad3bdbdbL, 0x9efa56fac8566464L, 0xa6d24ed2e84e7474L, 0x36221e22281e1414L,
        0xe476db763fdb9292L, 0x121e0a1e180a0c0cL, 0xfcb46cb4906c4848L, 0x8f37e4376be4b8b8L,
        0x78e75de7255d9f9fL, 0x0fb26eb2616ebdbdL, 0x692aef2a86ef4343L, 0x35f1a6f193a6c4c4L,
        0xdae3a8e372a83939L, 0xc6f7a4f762a43131L, 0x8a593759bd37d3d3L, 0x74868b86ff8bf2f2L,
        0x83563256b132d5d5L, 0x4ec543c50d438b8bL, 0x85eb59ebdc596e6eL, 0x18c2b7c2afb7dadaL,
        0x8e8f8c8f028c0101L, 0x1dac64ac7964b1b1L, 0xf16dd26d23d29c9cL, 0x723be03b92e04949L,
        0x1fc7b4c7abb4d8d8L, 0xb915fa1543faacacL, 0xfa090709fd07f3f3L, 0xa06f256f8525cfcfL,
        0x20eaafea8fafcacaL, 0x7d898e89f38ef4f4L, 0x6720e9208ee94747L, 0x3828182820181010L,
        0x0b64d564ded56f6fL, 0x73838883fb88f0f0L, 0xfbb16fb1946f4a4aL, 0xca967296b8725c5cL,
        0x546c246c70243838L, 0x5f08f108aef15757L, 0x2152c752e6c77373L, 0x64f351f335519797L,
        0xae6523658d23cbcbL, 0x25847c84597ca1a1L, 0x57bf9cbfcb9ce8e8L, 0x5d6321637c213e3eL,
        0xea7cdd7c37dd9696L, 0x1e7fdc7fc2dc6161L, 0x9c9186911a860d0dL, 0x9b9485941e850f0fL,
        0x4bab90abdb90e0e0L, 0xbac642c6f8427c7cL, 0x2657c457e2c47171L, 0x29e5aae583aaccccL,
        0xe373d8733bd89090L, 0x090f050f0c050606L, 0xf4030103f501f7f7L, 0x2a36123638121c1cL,
        0x3cfea3fe9fa3c2c2L, 0x8be15fe1d45f6a6aL, 0xbe10f91047f9aeaeL, 0x026bd06bd2d06969L,
        0xbfa891a82e911717L, 0x71e858e829589999L, 0x5369276974273a3aL, 0xf7d0b9d04eb92727L,
        0x91483848a938d9d9L, 0xde351335cd13ebebL, 0xe5ceb3ce56b32b2bL, 0x7755335544332222L,
        0x04d6bbd6bfbbd2d2L, 0x399070904970a9a9L, 0x878089800e890707L, 0xc1f2a7f266a73333L,
        0xecc1b6c15ab62d2dL, 0x5a66226678223c3cL, 0xb8ad92ad2a921515L, 0xa96020608920c9c9L,
        0x5cdb49db15498787L, 0xb01aff1a4fffaaaaL, 0xd8887888a0785050L, 0x2b8e7a8e517aa5a5L,
        0x898a8f8a068f0303L, 0x4a13f813b2f85959L, 0x929b809b12800909L, 0x2339173934171a1aL,
        0x1075da75cada6565L, 0x84533153b531d7d7L, 0xd551c65113c68484L, 0x03d3b8d3bbb8d0d0L,
        0xdc5ec35e1fc38282L, 0xe2cbb0cb52b02929L, 0xc3997799b4775a5aL, 0x2d3311333c111e1eL,
        0x3d46cb46f6cb7b7bL, 0xb71ffc1f4bfca8a8L, 0x0c61d661dad66d6dL, 0x624e3a4e583a2c2cL
    };

    private static void p512round(long[] x, long[] y, long r) {
        x[0] ^= r;
        x[1] ^= 0x1000000000000000L ^ r;
        x[2] ^= 0x2000000000000000L ^ r;
        x[3] ^= 0x3000000000000000L ^ r;
        x[4] ^= 0x4000000000000000L ^ r;
        x[5] ^= 0x5000000000000000L ^ r;
        x[6] ^= 0x6000000000000000L ^ r;
        x[7] ^= 0x7000000000000000L ^ r;

        y[0] = T0[(int) (x[0] >>> 56)]
                ^ T1[(int) (x[1] >>> 48) & 0xff]
                ^ T2[(int) (x[2] >>> 40) & 0xff]
                ^ T3[(int) (x[3] >>> 32) & 0xff]
                ^ T4[(int) (x[4] >>> 24) & 0xff]
                ^ T5[(int) (x[5] >>> 16) & 0xff]
                ^ T6[(int) (x[6] >>> 8) & 0xff]
                ^ T7[(int) x[7] & 0xff];
        y[1] = T0[(int) (x[1] >>> 56)]
                ^ T1[(int) (x[2] >>> 48) & 0xff]
                ^ T2[(int) (x[3] >>> 40) & 0xff]
                ^ T3[(int) (x[4] >>> 32) & 0xff]
                ^ T4[(int) (x[5] >>> 24) & 0xff]
                ^ T5[(int) (x[6] >>> 16) & 0xff]
                ^ T6[(int) (x[7] >>> 8) & 0xff]
                ^ T7[(int) x[0] & 0xff];
        y[2] = T0[(int) (x[2] >>> 56)]
                ^ T1[(int) (x[3] >>> 48) & 0xff]
                ^ T2[(int) (x[4] >>> 40) & 0xff]
                ^ T3[(int) (x[5] >>> 32) & 0xff]
                ^ T4[(int) (x[6] >>> 24) & 0xff]
                ^ T5[(int) (x[7] >>> 16) & 0xff]
                ^ T6[(int) (x[0] >>> 8) & 0xff]
                ^ T7[(int) x[1] & 0xff];
        y[3] = T0[(int) (x[3] >>> 56)]
                ^ T1[(int) (x[4] >>> 48) & 0xff]
                ^ T2[(int) (x[5] >>> 40) & 0xff]
                ^ T3[(int) (x[6] >>> 32) & 0xff]
                ^ T4[(int) (x[7] >>> 24) & 0xff]
                ^ T5[(int) (x[0] >>> 16) & 0xff]
                ^ T6[(int) (x[1] >>> 8) & 0xff]
                ^ T7[(int) x[2] & 0xff];
        y[4] = T0[(int) (x[4] >>> 56)]
                ^ T1[(int) (x[5] >>> 48) & 0xff]
                ^ T2[(int) (x[6] >>> 40) & 0xff]
                ^ T3[(int) (x[7] >>> 32) & 0xff]
                ^ T4[(int) (x[0] >>> 24) & 0xff]
                ^ T5[(int) (x[1] >>> 16) & 0xff]
                ^ T6[(int) (x[2] >>> 8) & 0xff]
                ^ T7[(int) x[3] & 0xff];
        y[5] = T0[(int) (x[5] >>> 56)]
                ^ T1[(int) (x[6] >>> 48) & 0xff]
                ^ T2[(int) (x[7] >>> 40) & 0xff]
                ^ T3[(int) (x[0] >>> 32) & 0xff]
                ^ T4[(int) (x[1] >>> 24) & 0xff]
                ^ T5[(int) (x[2] >>> 16) & 0xff]
                ^ T6[(int) (x[3] >>> 8) & 0xff]
                ^ T7[(int) x[4] & 0xff];
        y[6] = T0[(int) (x[6] >>> 56)]
                ^ T1[(int) (x[7] >>> 48) & 0xff]
                ^ T2[(int) (x[0] >>> 40) & 0xff]
                ^ T3[(int) (x[1] >>> 32) & 0xff]
                ^ T4[(int) (x[2] >>> 24) & 0xff]
                ^ T5[(int) (x[3] >>> 16) & 0xff]
                ^ T6[(int) (x[4] >>> 8) & 0xff]
                ^ T7[(int) x[5] & 0xff];
        y[7] = T0[(int) (x[7] >>> 56)]
                ^ T1[(int) (x[0] >>> 48) & 0xff]
                ^ T2[(int) (x[1] >>> 40) & 0xff]
                ^ T3[(int) (x[2] >>> 32) & 0xff]
                ^ T4[(int) (x[3] >>> 24) & 0xff]
                ^ T5[(int) (x[4] >>> 16) & 0xff]
                ^ T6[(int) (x[5] >>> 8) & 0xff]
                ^ T7[(int) x[6] & 0xff];
    }

    private static void q512round(long[] x, long[] y, long r) {
        x[0] ^= 0xffffffffffffffffL ^ r;
        x[1] ^= 0xffffffffffffffefL ^ r;
        x[2] ^= 0xffffffffffffffdfL ^ r;
        x[3] ^= 0xffffffffffffffcfL ^ r;
        x[4] ^= 0xffffffffffffffbfL ^ r;
        x[5] ^= 0xffffffffffffffafL ^ r;
        x[6] ^= 0xffffffffffffff9fL ^ r;
        x[7] ^= 0xffffffffffffff8fL ^ r;

        y[0] = T0[(int) (x[1] >>> 56)]
                ^ T1[(int) (x[3] >>> 48) & 0xff]
                ^ T2[(int) (x[5] >>> 40) & 0xff]
                ^ T3[(int) (x[7] >>> 32) & 0xff]
                ^ T4[(int) (x[0] >>> 24) & 0xff]
                ^ T5[(int) (x[2] >>> 16) & 0xff]
                ^ T6[(int) (x[4] >>> 8) & 0xff]
                ^ T7[(int) x[6] & 0xff];
        y[1] = T0[(int) (x[2] >>> 56)]
                ^ T1[(int) (x[4] >>> 48) & 0xff]
                ^ T2[(int) (x[6] >>> 40) & 0xff]
                ^ T3[(int) (x[0] >>> 32) & 0xff]
                ^ T4[(int) (x[1] >>> 24) & 0xff]
                ^ T5[(int) (x[3] >>> 16) & 0xff]
                ^ T6[(int) (x[5] >>> 8) & 0xff]
                ^ T7[(int) x[7] & 0xff];
        y[2] = T0[(int) (x[3] >>> 56)]
                ^ T1[(int) (x[5] >>> 48) & 0xff]
                ^ T2[(int) (x[7] >>> 40) & 0xff]
                ^ T3[(int) (x[1] >>> 32) & 0xff]
                ^ T4[(int) (x[2] >>> 24) & 0xff]
                ^ T5[(int) (x[4] >>> 16) & 0xff]
                ^ T6[(int) (x[6] >>> 8) & 0xff]
                ^ T7[(int) x[0] & 0xff];
        y[3] = T0[(int) (x[4] >>> 56)]
                ^ T1[(int) (x[6] >>> 48) & 0xff]
                ^ T2[(int) (x[0] >>> 40) & 0xff]
                ^ T3[(int) (x[2] >>> 32) & 0xff]
                ^ T4[(int) (x[3] >>> 24) & 0xff]
                ^ T5[(int) (x[5] >>> 16) & 0xff]
                ^ T6[(int) (x[7] >>> 8) & 0xff]
                ^ T7[(int) x[1] & 0xff];
        y[4] = T0[(int) (x[5] >>> 56)]
                ^ T1[(int) (x[7] >>> 48) & 0xff]
                ^ T2[(int) (x[1] >>> 40) & 0xff]
                ^ T3[(int) (x[3] >>> 32) & 0xff]
                ^ T4[(int) (x[4] >>> 24) & 0xff]
                ^ T5[(int) (x[6] >>> 16) & 0xff]
                ^ T6[(int) (x[0] >>> 8) & 0xff]
                ^ T7[(int) x[2] & 0xff];
        y[5] = T0[(int) (x[6] >>> 56)]
                ^ T1[(int) (x[0] >>> 48) & 0xff]
                ^ T2[(int) (x[2] >>> 40) & 0xff]
                ^ T3[(int) (x[4] >>> 32) & 0xff]
                ^ T4[(int) (x[5] >>> 24) & 0xff]
                ^ T5[(int) (x[7] >>> 16) & 0xff]
                ^ T6[(int) (x[1] >>> 8) & 0xff]
                ^ T7[(int) x[3] & 0xff];
        y[6] = T0[(int) (x[7] >>> 56)]
                ^ T1[(int) (x[1] >>> 48) & 0xff]
                ^ T2[(int) (x[3] >>> 40) & 0xff]
                ^ T3[(int) (x[5] >>> 32) & 0xff]
                ^ T4[(int) (x[6] >>> 24) & 0xff]
                ^ T5[(int) (x[0] >>> 16) & 0xff]
                ^ T6[(int) (x[2] >>> 8) & 0xff]
                ^ T7[(int) x[4] & 0xff];
        y[7] = T0[(int) (x[0] >>> 56)]
                ^ T1[(int) (x[2] >>> 48) & 0xff]
                ^ T2[(int) (x[4] >>> 40) & 0xff]
                ^ T3[(int) (x[6] >>> 32) & 0xff]
                ^ T4[(int) (x[7] >>> 24) & 0xff]
                ^ T5[(int) (x[1] >>> 16) & 0xff]
                ^ T6[(int) (x[3] >>> 8) & 0xff]
                ^ T7[(int) x[5] & 0xff];
    }

    private static void p1024round(long[] x, long[] y, long r) {
        x[0] ^= r;
        x[1] ^= 0x1000000000000000L ^ r;
        x[2] ^= 0x2000000000000000L ^ r;
        x[3] ^= 0x3000000000000000L ^ r;
        x[4] ^= 0x4000000000000000L ^ r;
        x[5] ^= 0x5000000000000000L ^ r;
        x[6] ^= 0x6000000000000000L ^ r;
        x[7] ^= 0x7000000000000000L ^ r;
        x[8] ^= 0x8000000000000000L ^ r;
        x[9] ^= 0x9000000000000000L ^ r;
        x[10] ^= 0xa000000000000000L ^ r;
        x[11] ^= 0xb000000000000000L ^ r;
        x[12] ^= 0xc000000000000000L ^ r;
        x[13] ^= 0xd000000000000000L ^ r;
        x[14] ^= 0xe000000000000000L ^ r;
        x[15] ^= 0xf000000000000000L ^ r;

        y[0] = T0[(int) (x[0] >>> 56)]
                ^ T1[(int) (x[1] >>> 48) & 0xff]
                ^ T2[(int) (x[2] >>> 40) & 0xff]
                ^ T3[(int) (x[3] >>> 32) & 0xff]
                ^ T4[(int) (x[4] >>> 24) & 0xff]
                ^ T5[(int) (x[5] >>> 16) & 0xff]
                ^ T6[(int) (x[6] >>> 8) & 0xff]
                ^ T7[(int) x[11] & 0xff];
        y[1] = T0[(int) (x[1] >>> 56)]
                ^ T1[(int) (x[2] >>> 48) & 0xff]
                ^ T2[(int) (x[3] >>> 40) & 0xff]
                ^ T3[(int) (x[4] >>> 32) & 0xff]
                ^ T4[(int) (x[5] >>> 24) & 0xff]
                ^ T5[(int) (x[6] >>> 16) & 0xff]
                ^ T6[(int) (x[7] >>> 8) & 0xff]
                ^ T7[(int) x[12] & 0xff];
        y[2] = T0[(int) (x[2] >>> 56)]
                ^ T1[(int) (x[3] >>> 48) & 0xff]
                ^ T2[(int) (x[4] >>> 40) & 0xff]
                ^ T3[(int) (x[5] >>> 32) & 0xff]
                ^ T4[(int) (x[6] >>> 24) & 0xff]
                ^ T5[(int) (x[7] >>> 16) & 0xff]
                ^ T6[(int) (x[8] >>> 8) & 0xff]
                ^ T7[(int) x[13] & 0xff];
        y[3] = T0[(int) (x[3] >>> 56)]
                ^ T1[(int) (x[4] >>> 48) & 0xff]
                ^ T2[(int) (x[5] >>> 40) & 0xff]
                ^ T3[(int) (x[6] >>> 32) & 0xff]
                ^ T4[(int) (x[7] >>> 24) & 0xff]
                ^ T5[(int) (x[8] >>> 16) & 0xff]
                ^ T6[(int) (x[9] >>> 8) & 0xff]
                ^ T7[(int) x[14] & 0xff];
        y[4] = T0[(int) (x[4] >>> 56)]
                ^ T1[(int) (x[5] >>> 48) & 0xff]
                ^ T2[(int) (x[6] >>> 40) & 0xff]
                ^ T3[(int) (x[7] >>> 32) & 0xff]
                ^ T4[(int) (x[8] >>> 24) & 0xff]
                ^ T5[(int) (x[9] >>> 16) & 0xff]
                ^ T6[(int) (x[10] >>> 8) & 0xff]
                ^ T7[(int) x[15] & 0xff];
        y[5] = T0[(int) (x[5] >>> 56)]
                ^ T1[(int) (x[6] >>> 48) & 0xff]
                ^ T2[(int) (x[7] >>> 40) & 0xff]
                ^ T3[(int) (x[8] >>> 32) & 0xff]
                ^ T4[(int) (x[9] >>> 24) & 0xff]
                ^ T5[(int) (x[10] >>> 16) & 0xff]
                ^ T6[(int) (x[11] >>> 8) & 0xff]
                ^ T7[(int) x[0] & 0xff];
        y[6] = T0[(int) (x[6] >>> 56)]
                ^ T1[(int) (x[7] >>> 48) & 0xff]
                ^ T2[(int) (x[8] >>> 40) & 0xff]
                ^ T3[(int) (x[9] >>> 32) & 0xff]
                ^ T4[(int) (x[10] >>> 24) & 0xff]
                ^ T5[(int) (x[11] >>> 16) & 0xff]
                ^ T6[(int) (x[12] >>> 8) & 0xff]
                ^ T7[(int) x[1] & 0xff];
        y[7] = T0[(int) (x[7] >>> 56)]
                ^ T1[(int) (x[8] >>> 48) & 0xff]
                ^ T2[(int) (x[9] >>> 40) & 0xff]
                ^ T3[(int) (x[10] >>> 32) & 0xff]
                ^ T4[(int) (x[11] >>> 24) & 0xff]
                ^ T5[(int) (x[12] >>> 16) & 0xff]
                ^ T6[(int) (x[13] >>> 8) & 0xff]
                ^ T7[(int) x[2] & 0xff];
        y[8] = T0[(int) (x[8] >>> 56)]
                ^ T1[(int) (x[9] >>> 48) & 0xff]
                ^ T2[(int) (x[10] >>> 40) & 0xff]
                ^ T3[(int) (x[11] >>> 32) & 0xff]
                ^ T4[(int) (x[12] >>> 24) & 0xff]
                ^ T5[(int) (x[13] >>> 16) & 0xff]
                ^ T6[(int) (x[14] >>> 8) & 0xff]
                ^ T7[(int) x[3] & 0xff];
        y[9] = T0[(int) (x[9] >>> 56)]
                ^ T1[(int) (x[10] >>> 48) & 0xff]
                ^ T2[(int) (x[11] >>> 40) & 0xff]
                ^ T3[(int) (x[12] >>> 32) & 0xff]
                ^ T4[(int) (x[13] >>> 24) & 0xff]
                ^ T5[(int) (x[14] >>> 16) & 0xff]
                ^ T6[(int) (x[15] >>> 8) & 0xff]
                ^ T7[(int) x[4] & 0xff];
        y[10] = T0[(int) (x[10] >>> 56)]
                ^ T1[(int) (x[11] >>> 48) & 0xff]
                ^ T2[(int) (x[12] >>> 40) & 0xff]
                ^ T3[(int) (x[13] >>> 32) & 0xff]
                ^ T4[(int) (x[14] >>> 24) & 0xff]
                ^ T5[(int) (x[15] >>> 16) & 0xff]
                ^ T6[(int) (x[0] >>> 8) & 0xff]
                ^ T7[(int) x[5] & 0xff];
        y[11] = T0[(int) (x[11] >>> 56)]
                ^ T1[(int) (x[12] >>> 48) & 0xff]
                ^ T2[(int) (x[13] >>> 40) & 0xff]
                ^ T3[(int) (x[14] >>> 32) & 0xff]
                ^ T4[(int) (x[15] >>> 24) & 0xff]
                ^ T5[(int) (x[0] >>> 16) & 0xff]
                ^ T6[(int) (x[1] >>> 8) & 0xff]
                ^ T7[(int) x[6] & 0xff];
        y[12] = T0[(int) (x[12] >>> 56)]
                ^ T1[(int) (x[13] >>> 48) & 0xff]
                ^ T2[(int) (x[14] >>> 40) & 0xff]
                ^ T3[(int) (x[15] >>> 32) & 0xff]
                ^ T4[(int) (x[0] >>> 24) & 0xff]
                ^ T5[(int) (x[1] >>> 16) & 0xff]
                ^ T6[(int) (x[2] >>> 8) & 0xff]
                ^ T7[(int) x[7] & 0xff];
        y[13] = T0[(int) (x[13] >>> 56)]
                ^ T1[(int) (x[14] >>> 48) & 0xff]
                ^ T2[(int) (x[15] >>> 40) & 0xff]
                ^ T3[(int) (x[0] >>> 32) & 0xff]
                ^ T4[(int) (x[1] >>> 24) & 0xff]
                ^ T5[(int) (x[2] >>> 16) & 0xff]
                ^ T6[(int) (x[3] >>> 8) & 0xff]
                ^ T7[(int) x[8] & 0xff];
        y[14] = T0[(int) (x[14] >>> 56)]
                ^ T1[(int) (x[15] >>> 48) & 0xff]
                ^ T2[(int) (x[0] >>> 40) & 0xff]
                ^ T3[(int) (x[1] >>> 32) & 0xff]
                ^ T4[(int) (x[2] >>> 24) & 0xff]
                ^ T5[(int) (x[3] >>> 16) & 0xff]
                ^ T6[(int) (x[4] >>> 8) & 0xff]
                ^ T7[(int) x[9] & 0xff];
        y[15] = T0[(int) (x[15] >>> 56)]
                ^ T1[(int) (x[0] >>> 48) & 0xff]
                ^ T2[(int) (x[1] >>> 40) & 0xff]
                ^ T3[(int) (x[2] >>> 32) & 0xff]
                ^ T4[(int) (x[3] >>> 24) & 0xff]
                ^ T5[(int) (x[4] >>> 16) & 0xff]
                ^ T6[(int) (x[5] >>> 8) & 0xff]
                ^ T7[(int) x[10] & 0xff];
    }

    private static void q1024round(long[] x, long[] y, long r) {
        x[0] ^= 0xffffffffffffffffL ^ r;
        x[1] ^= 0xffffffffffffffefL ^ r;
        x[2] ^= 0xffffffffffffffdfL ^ r;
        x[3] ^= 0xffffffffffffffcfL ^ r;
        x[4] ^= 0xffffffffffffffbfL ^ r;
        x[5] ^= 0xffffffffffffffafL ^ r;
        x[6] ^= 0xffffffffffffff9fL ^ r;
        x[7] ^= 0xffffffffffffff8fL ^ r;
        x[8] ^= 0xffffffffffffff7fL ^ r;
        x[9] ^= 0xffffffffffffff6fL ^ r;
        x[10] ^= 0xffffffffffffff5fL ^ r;
        x[11] ^= 0xffffffffffffff4fL ^ r;
        x[12] ^= 0xffffffffffffff3fL ^ r;
        x[13] ^= 0xffffffffffffff2fL ^ r;
        x[14] ^= 0xffffffffffffff1fL ^ r;
        x[15] ^= 0xffffffffffffff0fL ^ r;

        y[0] = T0[(int) (x[1] >>> 56)]
                ^ T1[(int) (x[3] >>> 48) & 0xff]
                ^ T2[(int) (x[5] >>> 40) & 0xff]
                ^ T3[(int) (x[11] >>> 32) & 0xff]
                ^ T4[(int) (x[0] >>> 24) & 0xff]
                ^ T5[(int) (x[2] >>> 16) & 0xff]
                ^ T6[(int) (x[4] >>> 8) & 0xff]
                ^ T7[(int) x[6] & 0xff];
        y[1] = T0[(int) (x[2] >>> 56)]
                ^ T1[(int) (x[4] >>> 48) & 0xff]
                ^ T2[(int) (x[6] >>> 40) & 0xff]
                ^ T3[(int) (x[12] >>> 32) & 0xff]
                ^ T4[(int) (x[1] >>> 24) & 0xff]
                ^ T5[(int) (x[3] >>> 16) & 0xff]
                ^ T6[(int) (x[5] >>> 8) & 0xff]
                ^ T7[(int) x[7] & 0xff];
        y[2] = T0[(int) (x[3] >>> 56)]
                ^ T1[(int) (x[5] >>> 48) & 0xff]
                ^ T2[(int) (x[7] >>> 40) & 0xff]
                ^ T3[(int) (x[13] >>> 32) & 0xff]
                ^ T4[(int) (x[2] >>> 24) & 0xff]
                ^ T5[(int) (x[4] >>> 16) & 0xff]
                ^ T6[(int) (x[6] >>> 8) & 0xff]
                ^ T7[(int) x[8] & 0xff];
        y[3] = T0[(int) (x[4] >>> 56)]
                ^ T1[(int) (x[6] >>> 48) & 0xff]
                ^ T2[(int) (x[8] >>> 40) & 0xff]
                ^ T3[(int) (x[14] >>> 32) & 0xff]
                ^ T4[(int) (x[3] >>> 24) & 0xff]
                ^ T5[(int) (x[5] >>> 16) & 0xff]
                ^ T6[(int) (x[7] >>> 8) & 0xff]
                ^ T7[(int) x[9] & 0xff];
        y[4] = T0[(int) (x[5] >>> 56)]
                ^ T1[(int) (x[7] >>> 48) & 0xff]
                ^ T2[(int) (x[9] >>> 40) & 0xff]
                ^ T3[(int) (x[15] >>> 32) & 0xff]
                ^ T4[(int) (x[4] >>> 24) & 0xff]
                ^ T5[(int) (x[6] >>> 16) & 0xff]
                ^ T6[(int) (x[8] >>> 8) & 0xff]
                ^ T7[(int) x[10] & 0xff];
        y[5] = T0[(int) (x[6] >>> 56)]
                ^ T1[(int) (x[8] >>> 48) & 0xff]
                ^ T2[(int) (x[10] >>> 40) & 0xff]
                ^ T3[(int) (x[0] >>> 32) & 0xff]
                ^ T4[(int) (x[5] >>> 24) & 0xff]
                ^ T5[(int) (x[7] >>> 16) & 0xff]
                ^ T6[(int) (x[9] >>> 8) & 0xff]
                ^ T7[(int) x[11] & 0xff];
        y[6] = T0[(int) (x[7] >>> 56)]
                ^ T1[(int) (x[9] >>> 48) & 0xff]
                ^ T2[(int) (x[11] >>> 40) & 0xff]
                ^ T3[(int) (x[1] >>> 32) & 0xff]
                ^ T4[(int) (x[6] >>> 24) & 0xff]
                ^ T5[(int) (x[8] >>> 16) & 0xff]
                ^ T6[(int) (x[10] >>> 8) & 0xff]
                ^ T7[(int) x[12] & 0xff];
        y[7] = T0[(int) (x[8] >>> 56)]
                ^ T1[(int) (x[10] >>> 48) & 0xff]
                ^ T2[(int) (x[12] >>> 40) & 0xff]
                ^ T3[(int) (x[2] >>> 32) & 0xff]
                ^ T4[(int) (x[7] >>> 24) & 0xff]
                ^ T5[(int) (x[9] >>> 16) & 0xff]
                ^ T6[(int) (x[11] >>> 8) & 0xff]
                ^ T7[(int) x[13] & 0xff];
        y[8] = T0[(int) (x[9] >>> 56)]
                ^ T1[(int) (x[11] >>> 48) & 0xff]
                ^ T2[(int) (x[13] >>> 40) & 0xff]
                ^ T3[(int) (x[3] >>> 32) & 0xff]
                ^ T4[(int) (x[8] >>> 24) & 0xff]
                ^ T5[(int) (x[10] >>> 16) & 0xff]
                ^ T6[(int) (x[12] >>> 8) & 0xff]
                ^ T7[(int) x[14] & 0xff];
        y[9] = T0[(int) (x[10] >>> 56)]
                ^ T1[(int) (x[12] >>> 48) & 0xff]
                ^ T2[(int) (x[14] >>> 40) & 0xff]
                ^ T3[(int) (x[4] >>> 32) & 0xff]
                ^ T4[(int) (x[9] >>> 24) & 0xff]
                ^ T5[(int) (x[11] >>> 16) & 0xff]
                ^ T6[(int) (x[13] >>> 8) & 0xff]
                ^ T7[(int) x[15] & 0xff];
        y[10] = T0[(int) (x[11] >>> 56)]
                ^ T1[(int) (x[13] >>> 48) & 0xff]
                ^ T2[(int) (x[15] >>> 40) & 0xff]
                ^ T3[(int) (x[5] >>> 32) & 0xff]
                ^ T4[(int) (x[10] >>> 24) & 0xff]
                ^ T5[(int) (x[12] >>> 16) & 0xff]
                ^ T6[(int) (x[14] >>> 8) & 0xff]
                ^ T7[(int) x[0] & 0xff];
        y[11] = T0[(int) (x[12] >>> 56)]
                ^ T1[(int) (x[14] >>> 48) & 0xff]
                ^ T2[(int) (x[0] >>> 40) & 0xff]
                ^ T3[(int) (x[6] >>> 32) & 0xff]
                ^ T4[(int) (x[11] >>> 24) & 0xff]
                ^ T5[(int) (x[13] >>> 16) & 0xff]
                ^ T6[(int) (x[15] >>> 8) & 0xff]
                ^ T7[(int) x[1] & 0xff];
        y[12] = T0[(int) (x[13] >>> 56)]
                ^ T1[(int) (x[15] >>> 48) & 0xff]
                ^ T2[(int) (x[1] >>> 40) & 0xff]
                ^ T3[(int) (x[7] >>> 32) & 0xff]
                ^ T4[(int) (x[12] >>> 24) & 0xff]
                ^ T5[(int) (x[14] >>> 16) & 0xff]
                ^ T6[(int) (x[0] >>> 8) & 0xff]
                ^ T7[(int) x[2] & 0xff];
        y[13] = T0[(int) (x[14] >>> 56)]
                ^ T1[(int) (x[0] >>> 48) & 0xff]
                ^ T2[(int) (x[2] >>> 40) & 0xff]
                ^ T3[(int) (x[8] >>> 32) & 0xff]
                ^ T4[(int) (x[13] >>> 24) & 0xff]
                ^ T5[(int) (x[15] >>> 16) & 0xff]
                ^ T6[(int) (x[1] >>> 8) & 0xff]
                ^ T7[(int) x[3] & 0xff];
        y[14] = T0[(int) (x[15] >>> 56)]
                ^ T1[(int) (x[1] >>> 48) & 0xff]
                ^ T2[(int) (x[3] >>> 40) & 0xff]
                ^ T3[(int) (x[9] >>> 32) & 0xff]
                ^ T4[(int) (x[14] >>> 24) & 0xff]
                ^ T5[(int) (x[0] >>> 16) & 0xff]
                ^ T6[(int) (x[2] >>> 8) & 0xff]
                ^ T7[(int) x[4] & 0xff];
        y[15] = T0[(int) (x[0] >>> 56)]
                ^ T1[(int) (x[2] >>> 48) & 0xff]
                ^ T2[(int) (x[4] >>> 40) & 0xff]
                ^ T3[(int) (x[10] >>> 32) & 0xff]
                ^ T4[(int) (x[15] >>> 24) & 0xff]
                ^ T5[(int) (x[1] >>> 16) & 0xff]
                ^ T6[(int) (x[3] >>> 8) & 0xff]
                ^ T7[(int) x[5] & 0xff];
    }

    private static abstract class Groest512Engine extends AbstractDigestEngine {

        private final long[] temp1 = new long[8], temp2 = new long[8], temp3 = new long[8];
        protected final long[] state;
        private long counter = 0;

        private Groest512Engine(long[] iv) {
            super(64);
            state = iv;
        }

        @Override
        protected void ingestOneBlock(MemorySegment input, long offset) {
            for (int i = 0; i < 8; i++) {
                temp1[i] = input.get(LAYOUT, offset + 8 * i);
                temp2[i] = state[i] ^ temp1[i];
            }

            q512round(temp1, temp3, 0L);
            q512round(temp3, temp1, 1L);
            q512round(temp1, temp3, 2L);
            q512round(temp3, temp1, 3L);
            q512round(temp1, temp3, 4L);
            q512round(temp3, temp1, 5L);
            q512round(temp1, temp3, 6L);
            q512round(temp3, temp1, 7L);
            q512round(temp1, temp3, 8L);
            q512round(temp3, temp1, 9L);

            p512round(temp2, temp3, 0x0000000000000000L);
            p512round(temp3, temp2, 0x0100000000000000L);
            p512round(temp2, temp3, 0x0200000000000000L);
            p512round(temp3, temp2, 0x0300000000000000L);
            p512round(temp2, temp3, 0x0400000000000000L);
            p512round(temp3, temp2, 0x0500000000000000L);
            p512round(temp2, temp3, 0x0600000000000000L);
            p512round(temp3, temp2, 0x0700000000000000L);
            p512round(temp2, temp3, 0x0800000000000000L);
            p512round(temp3, temp2, 0x0900000000000000L);

            for (int i = 0; i < 8; i++) {
                state[i] ^= temp1[i] ^ temp2[i];
            }

            counter++;
        }

        @Override
        protected void ingestLastBlock(MemorySegment input, int length) {
            if (length == 64) {
                ingestOneBlock(input, 0);
                length = 0;
            }
            if (length >= 56) {
                Tools.ozpad(input, length);
                ingestOneBlock(input, 0);
                input.asSlice(0, 56).fill((byte) 0);
            } else {
                Tools.ozpad(input.asSlice(0, 56), length);
            }
            input.set(LAYOUT, 56, ++counter);
            ingestOneBlock(input, 0);

            p512round(state, temp1, 0x0000000000000000L);
            p512round(temp1, temp2, 0x0100000000000000L);
            p512round(temp2, temp1, 0x0200000000000000L);
            p512round(temp1, temp2, 0x0300000000000000L);
            p512round(temp2, temp1, 0x0400000000000000L);
            p512round(temp1, temp2, 0x0500000000000000L);
            p512round(temp2, temp1, 0x0600000000000000L);
            p512round(temp1, temp2, 0x0700000000000000L);
            p512round(temp2, temp1, 0x0800000000000000L);
            p512round(temp1, temp2, 0x0900000000000000L);

            for (int i = 0; i < 8; i++) {
                state[i] ^= temp2[i];
            }
        }

    }

    private static abstract class Groest1024Engine extends AbstractDigestEngine {

        private final long[] temp1 = new long[16], temp2 = new long[16], temp3 = new long[16];
        protected final long[] state;
        private long counter = 0;

        private Groest1024Engine(long[] iv) {
            super(128);
            state = iv;
        }

        @Override
        protected void ingestOneBlock(MemorySegment input, long offset) {
            for (int i = 0; i < 16; i++) {
                temp1[i] = input.get(LAYOUT, offset + 8 * i);
                temp2[i] = state[i] ^ temp1[i];
            }

            q1024round(temp1, temp3, 0L);
            q1024round(temp3, temp1, 1L);
            q1024round(temp1, temp3, 2L);
            q1024round(temp3, temp1, 3L);
            q1024round(temp1, temp3, 4L);
            q1024round(temp3, temp1, 5L);
            q1024round(temp1, temp3, 6L);
            q1024round(temp3, temp1, 7L);
            q1024round(temp1, temp3, 8L);
            q1024round(temp3, temp1, 9L);
            q1024round(temp1, temp3, 10L);
            q1024round(temp3, temp1, 11L);
            q1024round(temp1, temp3, 12L);
            q1024round(temp3, temp1, 13L);

            p1024round(temp2, temp3, 0x0000000000000000L);
            p1024round(temp3, temp2, 0x0100000000000000L);
            p1024round(temp2, temp3, 0x0200000000000000L);
            p1024round(temp3, temp2, 0x0300000000000000L);
            p1024round(temp2, temp3, 0x0400000000000000L);
            p1024round(temp3, temp2, 0x0500000000000000L);
            p1024round(temp2, temp3, 0x0600000000000000L);
            p1024round(temp3, temp2, 0x0700000000000000L);
            p1024round(temp2, temp3, 0x0800000000000000L);
            p1024round(temp3, temp2, 0x0900000000000000L);
            p1024round(temp2, temp3, 0x0a00000000000000L);
            p1024round(temp3, temp2, 0x0b00000000000000L);
            p1024round(temp2, temp3, 0x0c00000000000000L);
            p1024round(temp3, temp2, 0x0d00000000000000L);

            for (int i = 0; i < 16; i++) {
                state[i] ^= temp1[i] ^ temp2[i];
            }

            counter++;
        }

        @Override
        protected void ingestLastBlock(MemorySegment input, int length) {
            if (length == 128) {
                ingestOneBlock(input, 0);
                length = 0;
            }
            if (length >= 120) {
                Tools.ozpad(input, length);
                ingestOneBlock(input, 0);
                input.asSlice(0, 120).fill((byte) 0);
            } else {
                Tools.ozpad(input.asSlice(0, 120), length);
            }
            input.set(LAYOUT, 120, ++counter);
            ingestOneBlock(input, 0);

            p1024round(state, temp1, 0x0000000000000000L);
            p1024round(temp1, temp2, 0x0100000000000000L);
            p1024round(temp2, temp1, 0x0200000000000000L);
            p1024round(temp1, temp2, 0x0300000000000000L);
            p1024round(temp2, temp1, 0x0400000000000000L);
            p1024round(temp1, temp2, 0x0500000000000000L);
            p1024round(temp2, temp1, 0x0600000000000000L);
            p1024round(temp1, temp2, 0x0700000000000000L);
            p1024round(temp2, temp1, 0x0800000000000000L);
            p1024round(temp1, temp2, 0x0900000000000000L);
            p1024round(temp2, temp1, 0x0a00000000000000L);
            p1024round(temp1, temp2, 0x0b00000000000000L);
            p1024round(temp2, temp1, 0x0c00000000000000L);
            p1024round(temp1, temp2, 0x0d00000000000000L);

            for (int i = 0; i < 16; i++) {
                state[i] ^= temp2[i];
            }
        }

    }

}
