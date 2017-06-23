package com.jwareconsulting.arusha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jwareconsulting.arusha.common.AppLog;
import com.squareup.picasso.Picasso;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppText;
import com.jwareconsulting.arusha.common.ValidationUtils;
import com.jwareconsulting.arusha.model.ServiceProviderModel;

import java.util.ArrayList;

/**
 * Created by anjulkhanal on 8/4/16.
 */
public class DirectoryLastPageAdapter extends RecyclerView.Adapter<DirectoryLastPageAdapter.ViewHolder> {
    private Context context;
    private String TAG = DirectoryLastPageAdapter.class.getSimpleName();
    private ArrayList<ServiceProviderModel> dataList;
    private OnItemClickListner onItemClickListner;


    public DirectoryLastPageAdapter(Context context) {
        this.context = context;
        dataList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_adapter_directory_detail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {

//        dataList.get(i);

        String title = dataList.get(i).getTitle();
        String vibeNumber = dataList.get(i).getViberNumber();
        String officeNumber = dataList.get(i).getOfficeNumber();
        String contact2 = dataList.get(i).getContact2();
        String phone = dataList.get(i).getServiceProvider();
        String skypeId = dataList.get(i).getSkypeId();
        String whatsappId = dataList.get(i).getWhatsappId();
        String location = dataList.get(i).getAddress();
        String mail = dataList.get(i).getEmailId();
        String web = dataList.get(i).getWebUrl();
        String description = dataList.get(i).getAdditionalDescription();
        String fburl = dataList.get(i).getFacebookUrl();
        String twitterurl = dataList.get(i).getTwitterUrl();
        String youtubeurl = dataList.get(i).getYoutubeUrl();
        String rank = "#rank";
        String tripAdvisorUrl = dataList.get(i).getTripAdvisiorURl();

        Picasso.with(context)
                .load(dataList.get(i).getImage())
                .into(holder.ivImage);


        if (dataList.get(i).getOpeningHour() != null) {

            String days;
            String time = "";
            String timing = "";
int size;

          /*  try {
                 size = dataList.get(i).getOpeningHour().size();
            } catch (Exception e){
                size=0;
            }
*/


            for (int j = 0; j < dataList.get(i).getOpeningHour().size(); j++) {

                days = dataList.get(i).getOpeningHour().get(j).getDays();
                timing = dataList.get(i).getOpeningHour().get(j).getTiming();
                /** String   temp = days + " : " + timing;
                 if (!ValidationUtils.isEmpty(days)) {
                 time = days;
                 if (!ValidationUtils.isEmpty(timing)) {
                 temp = time + " :" + timing;
                 }


                 } else if (!ValidationUtils.isEmpty(timing)) {
                 temp = timing;
                 }*/

                if (ValidationUtils.isEmpty(timing)) {
                    timing = "\n";

                } else {
                    timing += "\n";
                }
                if (ValidationUtils.isEmpty(days)) {
                    days = "";
                }


                time += days +" "+ timing;

            }


            holder.tvTime.setText(time);
            setVisibility(holder.ivTime, holder.tvTime, View.VISIBLE);
        } else {
            setVisibility(holder.ivTime, holder.tvTime, View.GONE);
        }


        if (!ValidationUtils.isEmpty(title)) {
            holder.tvTitle.setText(title);
        } else {
            holder.tvTitle.setText("");
        }


        if (!ValidationUtils.isEmpty(phone)) {
            holder.tvPhone.setText(phone);
            setVisibility(holder.ivPhone, holder.tvPhone, View.VISIBLE);
        } else {
            setVisibility(holder.ivPhone, holder.tvPhone, View.GONE);
        }

        if (!ValidationUtils.isEmpty(vibeNumber)) {
            holder.tvViber.setText(vibeNumber);
            setVisibility(holder.ivViber, holder.tvViber, View.VISIBLE);
        } else {
            setVisibility(holder.ivViber, holder.tvViber, View.GONE);
        }
        if (!ValidationUtils.isEmpty(officeNumber)) {
            holder.tvOfficeNumber.setText(officeNumber);
            setVisibility(holder.ivOffice, holder.tvOfficeNumber, View.VISIBLE);
        } else {
            setVisibility(holder.ivOffice, holder.tvOfficeNumber, View.GONE);
        }

        if (!ValidationUtils.isEmpty(contact2)) {
            holder.tvContact2.setText(contact2);
            setVisibility(holder.ivContact2, holder.tvContact2, View.VISIBLE);
        } else {
            setVisibility(holder.ivContact2, holder.tvContact2, View.GONE);
        }
        if (!ValidationUtils.isEmpty(skypeId)) {
            holder.tvSkype.setText(skypeId);
            setVisibility(holder.ivSkype, holder.tvSkype, View.VISIBLE);
        } else {
            setVisibility(holder.ivSkype, holder.tvSkype, View.GONE);
        }


        if (!ValidationUtils.isEmpty(whatsappId)) {
            holder.tvWhatsApp.setText(whatsappId);
            setVisibility(holder.ivWhatsApp, holder.tvWhatsApp, View.VISIBLE);
        } else {
            setVisibility(holder.ivWhatsApp, holder.tvWhatsApp, View.GONE);
        }

        if (!ValidationUtils.isEmpty(location)) {
            holder.tvLocation.setText(location);
            setVisibility(holder.ivLocation, holder.tvLocation, View.VISIBLE);
        } else {
            setVisibility(holder.ivLocation, holder.tvLocation, View.GONE);
        }

        if (!ValidationUtils.isEmpty(mail)) {
            holder.tvMail.setText(mail);
            setVisibility(holder.ivMail, holder.tvMail, View.VISIBLE);
        } else {
            setVisibility(holder.ivMail, holder.tvMail, View.GONE);
        }

        if (!ValidationUtils.isEmpty(web)) {
            holder.tvWeb.setText(web);
            setVisibility(holder.ivWeb, holder.tvWeb, View.VISIBLE);
        } else {
            setVisibility(holder.ivWeb, holder.tvWeb, View.GONE);
        }

//        if (!ValidationUtils.isEmpty(rank)) {
//            holder.tvRank.setText(rank);
//            setVisibility(holder.ivTripAdvisor, holder.tvRank, View.VISIBLE);
//        } else {
//            setVisibility(holder.ivTripAdvisor, holder.tvRank, View.GONE);
//        }

        if (!ValidationUtils.isEmpty(description)) {
            holder.tvDiscription.setText(description);
            holder.tvDiscription.setVisibility(View.VISIBLE);
        } else {
            holder.tvDiscription.setVisibility(View.GONE);
        }
        if (!ValidationUtils.isEmpty(fburl)) {
            holder.ivFacebook.setImageResource(R.drawable.fb_act);
        } else {
            holder.ivFacebook.setEnabled(false);
        }
        if (!ValidationUtils.isEmpty(twitterurl)) {
            holder.ivTwiter.setImageResource(R.drawable.twitter_act);
        } else {
            holder.ivTwiter.setEnabled(false);
        }
        if (!ValidationUtils.isEmpty(youtubeurl)) {
            holder.ivYoutube.setImageResource(R.drawable.youtube_act);
        } else {
            holder.ivYoutube.setEnabled(false);
        }
        if (!ValidationUtils.isEmpty(tripAdvisorUrl)) {
            holder.ivTripAdvisor.setImageResource(R.drawable.trip_advisor_act);
        } else {
            holder.ivTripAdvisor.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void populateView(ArrayList<ServiceProviderModel> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setVisibility(ImageView imageView, TextView textView, int visibility) {
        textView.setVisibility(visibility);
        imageView.setVisibility(visibility);
    }


    public interface OnItemClickListner {
        void onClick(int intentType, String data);

        void openInMap(String latitude, String longitude, String title);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvPhone, tvSkype, tvWhatsApp, tvLocation, tvTime, tvMail, tvWeb, tvDiscription, tvRank, tvViber, tvOfficeNumber, tvContact2;
        ImageView ivImage, ivPhone, ivSkype, ivWhatsApp, ivLocation, ivTime, ivMail, ivWeb, ivTripAdvisor, ivFacebook, ivTwiter, ivYoutube, ivViber, ivOffice, ivContact2;

        public ViewHolder(View itemView) {
            super(itemView);
//            itemView.findViewById(R.id.tv_)
            tvViber = (TextView) itemView.findViewById(R.id.tv_viber);
            tvOfficeNumber = (TextView) itemView.findViewById(R.id.tv_office);
            tvContact2 = (TextView) itemView.findViewById(R.id.tv_contact2);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            tvSkype = (TextView) itemView.findViewById(R.id.tv_skype);
            tvWhatsApp = (TextView) itemView.findViewById(R.id.tv_whatsapp);
            tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvMail = (TextView) itemView.findViewById(R.id.tv_mail);
            tvWeb = (TextView) itemView.findViewById(R.id.tv_web);
            tvDiscription = (TextView) itemView.findViewById(R.id.tv_description);
            tvRank = (TextView) itemView.findViewById(R.id.tv_rank);


            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            ivViber = (ImageView) itemView.findViewById(R.id.iv_viber);
            ivOffice = (ImageView) itemView.findViewById(R.id.iv_office);
            ivPhone = (ImageView) itemView.findViewById(R.id.iv_phone);
            ivContact2 = (ImageView) itemView.findViewById(R.id.iv_contact2);
            ivSkype = (ImageView) itemView.findViewById(R.id.iv_skype);
            ivWhatsApp = (ImageView) itemView.findViewById(R.id.iv_whats_app);
            ivLocation = (ImageView) itemView.findViewById(R.id.iv_location);
            ivTime = (ImageView) itemView.findViewById(R.id.iv_time);
            ivMail = (ImageView) itemView.findViewById(R.id.iv_mail);
            ivWeb = (ImageView) itemView.findViewById(R.id.iv_web);
            ivTripAdvisor = (ImageView) itemView.findViewById(R.id.iv_trip_advisor);

            ivFacebook = (ImageView) itemView.findViewById(R.id.iv_facebook);
            ivTwiter = (ImageView) itemView.findViewById(R.id.iv_twitter);
            ivYoutube = (ImageView) itemView.findViewById(R.id.iv_youtube);

            tvViber.setOnClickListener(this);
            tvOfficeNumber.setOnClickListener(this);

            tvPhone.setOnClickListener(this);
            tvContact2.setOnClickListener(this);
            tvOfficeNumber.setOnClickListener(this);
            tvSkype.setOnClickListener(this);
            tvWhatsApp.setOnClickListener(this);
            tvLocation.setOnClickListener(this);
            tvMail.setOnClickListener(this);
            tvWeb.setOnClickListener(this);
            ivTripAdvisor.setOnClickListener(this);
            ivFacebook.setOnClickListener(this);
            ivTwiter.setOnClickListener(this);
            ivYoutube.setOnClickListener(this);
//todo facebook , twitter. check first their alpha value .if their alpha value is not 100% do nothing.
        }


        @Override
        public void onClick(View v) {
            if (onItemClickListner != null) {
                int i = getAdapterPosition();
                switch (v.getId()) {

                    case R.id.tv_phone:
                        onItemClickListner.onClick(AppText.MAKE_CALL, dataList.get(i).getServiceProvider());
                        break;
                    case R.id.tv_skype:
                        onItemClickListner.onClick(AppText.SKYPE_CALL, dataList.get(i).getSkypeId());
                        break;
                    case R.id.tv_whatsapp:
                        onItemClickListner.onClick(AppText.WHATSAPP_CALL, dataList.get(i).getWhatsappId());
                        break;

                    case R.id.tv_mail:
                        onItemClickListner.onClick(AppText.INTENT_MAIL, dataList.get(i).getEmailId());
                        break;

                    case R.id.tv_web:
                        onItemClickListner.onClick(AppText.INTENT_BROWSER, dataList.get(i).getWebUrl());
                        break;
                    case R.id.tv_rank:
                        onItemClickListner.onClick(AppText.INTENT_BROWSER, dataList.get(i).getWebUrl());
                        break;
                    case R.id.iv_trip_advisor:
                        onItemClickListner.onClick(AppText.INTENT_BROWSER, dataList.get(i).getTripAdvisiorURl());
                        break;
                    case R.id.iv_facebook:
                        onItemClickListner.onClick(AppText.INTENT_BROWSER, dataList.get(i).getFacebookUrl());
                        break;
                    case R.id.iv_twitter:
                        onItemClickListner.onClick(AppText.INTENT_BROWSER, dataList.get(i).getTwitterUrl());
                        break;
                    case R.id.iv_youtube:
                        onItemClickListner.onClick(AppText.INTENT_BROWSER, dataList.get(i).getYoutubeUrl());
                        break;
                    case R.id.tv_location:
                        onItemClickListner.openInMap(dataList.get(i).getLocation().getLat(), dataList.get(i).getLocation().getLon(), dataList.get(i).getAddress());
                        break;
                    case R.id.tv_office:
                        onItemClickListner.onClick(AppText.MAKE_CALL, dataList.get(i).getOfficeNumber());
                        break;
                    case R.id.tv_viber:
                        onItemClickListner.onClick(AppText.VIBER_CALL, dataList.get(i).getViberNumber());
                        break;
                    case R.id.tv_contact2:
                        onItemClickListner.onClick(AppText.MAKE_CALL, dataList.get(i).getContact2());
                        break;

                }
            }

/*
            if (onItemClickListner != null) {

                onItemClickListner.onClick(getAdapterPosition());
            }*/
        }
    }

//
//    public void setFilter(List<ServiceProviderModel> dataList) {
//        dataList = new ArrayList<>();
//        dataList.addAll(dataList);
//        notifyDataSetChanged();
//    }
}
