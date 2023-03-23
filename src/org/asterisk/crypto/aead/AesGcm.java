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
package org.asterisk.crypto.aead;

import java.util.Arrays;

/**
 *
 * @author Sayantan Chakraborty
 */
public class AesGcm {

    private static final byte[] mask = {
        (byte)0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01
    };

    private static final byte[] poly = {
        0x00, (byte)0xE1
    };

    private static final int[] GCM_SHIFT_TABLE = {
        0x0000, 0x01c2, 0x0384, 0x0246, 0x0708, 0x06ca, 0x048c, 0x054e, 0x0e10, 0x0fd2, 0x0d94, 0x0c56, 0x0918, 0x08da, 0x0a9c, 0x0b5e,
        0x1c20, 0x1de2, 0x1fa4, 0x1e66, 0x1b28, 0x1aea, 0x18ac, 0x196e, 0x1230, 0x13f2, 0x11b4, 0x1076, 0x1538, 0x14fa, 0x16bc, 0x177e,
        0x3840, 0x3982, 0x3bc4, 0x3a06, 0x3f48, 0x3e8a, 0x3ccc, 0x3d0e, 0x3650, 0x3792, 0x35d4, 0x3416, 0x3158, 0x309a, 0x32dc, 0x331e,
        0x2460, 0x25a2, 0x27e4, 0x2626, 0x2368, 0x22aa, 0x20ec, 0x212e, 0x2a70, 0x2bb2, 0x29f4, 0x2836, 0x2d78, 0x2cba, 0x2efc, 0x2f3e,
        0x7080, 0x7142, 0x7304, 0x72c6, 0x7788, 0x764a, 0x740c, 0x75ce, 0x7e90, 0x7f52, 0x7d14, 0x7cd6, 0x7998, 0x785a, 0x7a1c, 0x7bde,
        0x6ca0, 0x6d62, 0x6f24, 0x6ee6, 0x6ba8, 0x6a6a, 0x682c, 0x69ee, 0x62b0, 0x6372, 0x6134, 0x60f6, 0x65b8, 0x647a, 0x663c, 0x67fe,
        0x48c0, 0x4902, 0x4b44, 0x4a86, 0x4fc8, 0x4e0a, 0x4c4c, 0x4d8e, 0x46d0, 0x4712, 0x4554, 0x4496, 0x41d8, 0x401a, 0x425c, 0x439e,
        0x54e0, 0x5522, 0x5764, 0x56a6, 0x53e8, 0x522a, 0x506c, 0x51ae, 0x5af0, 0x5b32, 0x5974, 0x58b6, 0x5df8, 0x5c3a, 0x5e7c, 0x5fbe,
        0xe100, 0xe0c2, 0xe284, 0xe346, 0xe608, 0xe7ca, 0xe58c, 0xe44e, 0xef10, 0xeed2, 0xec94, 0xed56, 0xe818, 0xe9da, 0xeb9c, 0xea5e,
        0xfd20, 0xfce2, 0xfea4, 0xff66, 0xfa28, 0xfbea, 0xf9ac, 0xf86e, 0xf330, 0xf2f2, 0xf0b4, 0xf176, 0xf438, 0xf5fa, 0xf7bc, 0xf67e,
        0xd940, 0xd882, 0xdac4, 0xdb06, 0xde48, 0xdf8a, 0xddcc, 0xdc0e, 0xd750, 0xd692, 0xd4d4, 0xd516, 0xd058, 0xd19a, 0xd3dc, 0xd21e,
        0xc560, 0xc4a2, 0xc6e4, 0xc726, 0xc268, 0xc3aa, 0xc1ec, 0xc02e, 0xcb70, 0xcab2, 0xc8f4, 0xc936, 0xcc78, 0xcdba, 0xcffc, 0xce3e,
        0x9180, 0x9042, 0x9204, 0x93c6, 0x9688, 0x974a, 0x950c, 0x94ce, 0x9f90, 0x9e52, 0x9c14, 0x9dd6, 0x9898, 0x995a, 0x9b1c, 0x9ade,
        0x8da0, 0x8c62, 0x8e24, 0x8fe6, 0x8aa8, 0x8b6a, 0x892c, 0x88ee, 0x83b0, 0x8272, 0x8034, 0x81f6, 0x84b8, 0x857a, 0x873c, 0x86fe,
        0xa9c0, 0xa802, 0xaa44, 0xab86, 0xaec8, 0xaf0a, 0xad4c, 0xac8e, 0xa7d0, 0xa612, 0xa454, 0xa596, 0xa0d8, 0xa11a, 0xa35c, 0xa29e,
        0xb5e0, 0xb422, 0xb664, 0xb7a6, 0xb2e8, 0xb32a, 0xb16c, 0xb0ae, 0xbbf0, 0xba32, 0xb874, 0xb9b6, 0xbcf8, 0xbd3a, 0xbf7c, 0xbebe
    };

    private static void rightShift(int[] src, int[] dst) {
        dst[3] = (src[3] >>> 1) | (src[2] << 31);
        dst[2] = (src[2] >>> 1) | (src[1] << 31);
        dst[1] = (src[1] >>> 1) | (src[0] << 31);
        dst[0] = src[0] >>> 1;
    }

    public static class BigMultiplier {

        private static int[] gcmGFMultByH(byte[] b, int[][] V) {
            var Z = new int[16];
            for (int x = 0; x < 128; x++) {
                if ((b[x >>> 3] & mask[x & 7]) != 0) {
                    Z[0] ^= V[x][0];
                    Z[1] ^= V[x][1];
                    Z[2] ^= V[x][2];
                    Z[3] ^= V[x][3];
                }
            }
            return Z;
        }

        private final int[][][] PC = new int[16][256][4];
        private volatile boolean closed = false;

        public BigMultiplier(byte[] H) {
            var V = new int[128][4];
            var B = new byte[16];
            Arrays.fill(B, (byte) 0);

            System.arraycopy(H, 0, V[0], 0, 16);

            for (int i = 1; i < 128; i++) {
                rightShift(V[i - 1], V[i]);
                V[i][0] ^= poly[V[i - 1][3] & 1];
            }

            for (int y = 0; y < 256; y++) {
                B[0] = (byte) y;
                PC[0][y] = gcmGFMultByH(B, V);
            }
            for (int x = 1; x < 16; x++) {
                for (int y = 0; y < 256; y++) {
                    int[] row1 = PC[x - 1][y];
                    int[] row2 = PC[x][y];
                    for (int z = 15; z > 0; z--) {
                        row2[z] = row1[z - 1];
                    }
                    row2[3] = (row1[3] >>> 8) | (row1[2] << 24);
                    row2[2] = (row1[2] >>> 8) | (row1[1] << 24);
                    row2[1] = (row1[1] >>> 8) | (row1[0] << 24);
                    row2[0] = (row1[0] >>> 8) ^ GCM_SHIFT_TABLE[row1[3] & 0xff];
                }
            }
        }

        public byte[] multiply(byte[] I) {
            if (closed) {
                throw new IllegalStateException("Already closed!!");
            }
            byte[] T = new byte[16];
            System.arraycopy(PC[0][I[0] & 0xff], 0, T, 0, 16);
            for (int x = 1; x < 16; x++) {
                int[] mul = PC[x][I[x] & 0xff];//and with (byte)0xff to make t positive
                for (int y = 0; y < 16; y++) {
                    T[y] ^= mul[y];
                }
            }
            return T;
        }

    }

}