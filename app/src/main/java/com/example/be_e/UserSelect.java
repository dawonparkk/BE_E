package com.example.be_e;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UserSelect extends AppCompatActivity {
    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3, adspin4;
    String choice_do = "";
    String choice_se = "";
    String choice_car = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_select);

        final Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner spin2 = (Spinner)findViewById(R.id.spinner2);
        final Spinner spin3 = (Spinner)findViewById(R.id.spinner3);
        final Spinner spin4 = (Spinner)findViewById(R.id.spinner4);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_theme, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);

        adspin2 = ArrayAdapter.createFromResource(this, R.array.spinner_do, android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adspin2);

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adspin2.getItem(i).equals("강원도")) {
                    choice_do = "강원도";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_gagnwondo, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (adspin2.getItem(i).equals("경기도")) {
                    choice_do = "경기도";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_ganggido, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("경상남도")) {
                    choice_do = "경상남도";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_gangsamnamdo, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("경상북도")) {
                    choice_do = "경상북도";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_gangsambokdo, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("광주광역시")) {
                    choice_do = "광주광역시";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_gangju, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("대구광역시")) {
                    choice_do = "대구광역시";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_daegu, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("부산광역시")) {
                    choice_do = "부산광역시";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_busan, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("서울특별시")) {
                    choice_do = "서울특별시";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_seoul, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("울산광역시")) {
                    choice_do = "울산광역시";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_wulsan, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("인천광역시")) {
                    choice_do = "인천광역시";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinnder_do_incheon, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("전라남도")) {
                    choice_do = "전라남도";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_junlanamdo, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("전라북도")) {
                    choice_do = "전라북도";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_junlabukco, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("충청남도")) {
                    choice_do = "충청남도";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_chungnam, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("충청북도")) {
                    choice_do = "충청북도";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_chungbuk, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("대전광역시")) {
                    choice_do = "대전광역시";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_daejun, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                } else if (adspin2.getItem(i).equals("제주특별자치도")) {
                    choice_do = "제주특별자치도";
                    adspin3 = ArrayAdapter.createFromResource(UserSelect.this, R.array.spinner_do_jaeju, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin3.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {


                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adspin4 = ArrayAdapter.createFromResource(this, R.array.spinner_car, android.R.layout.simple_spinner_dropdown_item);
        adspin4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin4.setAdapter(adspin4);
    }
}
