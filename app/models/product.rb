class Product < ApplicationRecord
  validates :name, presence: true
  validates :description, presence: true
  belongs_to :user

  has_one_attached :photo do |photo|
    photo.variant :thumb, resize_to_limit: [100, 100]
    photo.variant :medium, resize_to_limit: [400, 400]
  end

  def price
    product_data&.default_price&.unit_amount&.fdiv(100.0)
  end

  def product_data
    return if data.blank?
    Stripe::Product.construct_from(JSON.parse(data))
  end
end
